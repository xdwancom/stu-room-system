package com.springweb.service.impl;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springweb.mapper.stuMapper;
import com.springweb.pojo.Result;
import com.springweb.pojo.Room;
import com.springweb.pojo.Stu;
import com.springweb.service.stuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)//自动事务管理,默认只有RuntimeException异常才回滚，设置rollbackFor可指定何种异常回滚，此处设置为全部
public class stuServiceImpl extends ServiceImpl<stuMapper,Stu> implements stuService {
    @Autowired//ioc容器管理，依赖注入
    private stuMapper stuMapper;

    /**
     * 新增/更新学生
     */
    @Override
    public Result add_or_update(Stu stu) {
        QueryWrapper<Stu> wrapper = new QueryWrapper<Stu>()
                .eq("roommsg", stu.getRoommsg())
                .ne("id", stu.getId());
        if (stuMapper.checkbedcount(stu)<=stuMapper.selectCount(wrapper))//检查该宿舍是否满人，若床位数≤宿舍除自己以外人数则满人
            return Result.error("countmax");
        return saveOrUpdate(stu)?Result.success():Result.error("更新失败");
    }

    /**
     * 条件查询学生
     */
    @Override
    public List<Stu> search(Stu stu) {
        return lambdaQuery()
                .eq(stu.getUnit() != null, Stu::getUnit, stu.getUnit())
                .eq(stu.getRoomid() != null, Stu::getRoomid, stu.getRoomid())
                .eq(stu.getId()!= null, Stu::getId, stu.getId())
                .eq(StringUtils.isNotBlank(stu.getName()), Stu::getName, stu.getName())
                .eq(StringUtils.isNotBlank(stu.getGender()), Stu::getGender, stu.getGender())
                .eq(stu.getAge() != null, Stu::getAge, stu.getAge())
                .eq(StringUtils.isNotBlank(stu.getDepartment()), Stu::getDepartment, stu.getDepartment())
                .eq(stu.getGrade() != null, Stu::getGrade, stu.getGrade())
                .eq(stu.getPhone() != null, Stu::getPhone, stu.getPhone())
                .eq(StringUtils.isNotBlank(stu.getRoommsg()), Stu::getRoommsg, stu.getRoommsg())
                .list();
    }

    /**
     * 按照年级/院系优先分配宿舍
     */
    @Override
    public Result assign(String method) {
        QueryWrapper<Stu> grade_mwrapper = new QueryWrapper<Stu>()
                .eq("gender", "男")
                .orderByAsc("grade","department","id");

        QueryWrapper<Stu> grade_swrapper = new QueryWrapper<Stu>()
            .eq("gender", "女")
            .orderByAsc("grade","department","id");

        QueryWrapper<Stu> dep_mwrapper = new QueryWrapper<Stu>()
                .eq("gender", "男")
                .orderByAsc("department","grade","id");

        QueryWrapper<Stu> dep_swrapper = new QueryWrapper<Stu>()
                .eq("gender", "女")
                .orderByAsc("department","grade","id");

        if (method.equals("grade")) {
            log.info("按照年级优先分配宿舍");
            return Result.success(assign(stuMapper.selectList(grade_mwrapper)/*临时男表*/,stuMapper.selectList(grade_swrapper)/*临时女表*/));//分配宿舍后返回学生表
        }else if (method.equals("dep")){
            log.info("按照院系优先分配宿舍");
            return Result.success(assign(stuMapper.selectList(dep_mwrapper)/*临时男表*/,stuMapper.selectList(dep_swrapper)/*临时女表*/));//分配宿舍后返回学生表
        }
        return Result.error("assign fail：please check url");//容错处理
    }

    /**
     * 分配宿舍方法，传入参数为排序好的男生列表和女生列表
     */
    private List<Stu> assign(List<Stu> mstu,List<Stu> fstu){
        List<Room> mroom= stuMapper.mroomlist();//临时男宿舍
        List<Room> froom= stuMapper.froomlist();//临时女宿舍

        for (int i=0,j=0; j<mroom.size(); j++) {//分配男生
            for (int k = 0; k < mroom.get(j).getBedcount(); k++) {//根据床位数添加指定数量学生
                mstu.get(i).setUnit(mroom.get(j).getUnit());
                mstu.get(i).setRoomid(mroom.get(j).getRoomid());
                mstu.get(i++).setRoommsg(mroom.get(j).getRoommsg());
                //System.out.println(mroom.get(j).getRoommsg());
                if (i==mstu.size())//分配完成则结束循环
                    break;
            }
            if (i==mstu.size())
                break;
        }


        for (int i=0,j=0;j<froom.size(); j++) {//分配女生
            for (int k = 0; k < froom.get(j).getBedcount(); k++) {//根据床位数添加指定数量学生
                fstu.get(i).setUnit(froom.get(j).getUnit());
                fstu.get(i).setRoomid(froom.get(j).getRoomid());
                fstu.get(i++).setRoommsg(froom.get(j).getRoommsg());
                //System.out.println(froom.get(j).getRoommsg());
                if (i==fstu.size())//分配完成则结束循环
                    break;
            }
            if (i==fstu.size())
                break;
        }

        stuMapper.delete(null);//清空stu(旧学生)表
        saveBatch(mstu);//批量插入
        saveBatch(fstu);
        return list();
    }

    @Override
    public Result upload(MultipartFile file) throws IOException {
        //用uuid构造唯一的文件名
        String originalFilename = file.getOriginalFilename();//获取原始文件名 - 1.jpg  123.0.0.jpg
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf(".")/*获取最后一个点的索引*/);//截取文件格式后缀并与uuid拼接

        String path="D:\\.文件夹\\xx\\java\\";
        log.info("存储路径: {}", path);
        log.info("新文件名: {}", newFileName);
        file.transferTo(new File(path+newFileName));//将文件存储在服务器的磁盘目录中
        return Result.success();
    }
}
