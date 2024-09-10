package com.springweb.service.impl;

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
public class stuServiceImpl implements stuService {
    @Autowired//ioc容器管理，依赖注入
    private stuMapper stuMapper;

    /**
     * 批量删除学生
     */
    @Override
    public void deleteid(List<Integer> ids) {
        stuMapper.deleteid(ids);
    }

    /**
     * 新增学生
     */
    @Override
    public Result add(Stu stu) {
        if (stuMapper.checkbedcount(stu)<=stuMapper.checkpeoplecount(stu))//检查该宿舍是否满人
            return Result.error("countmax");
        else if (stuMapper.id_count(stu.getId()))// 检查是否存在相同学号的学生信息
            return Result.error("id_repeat");
        stuMapper.insert(stu);
        return Result.success();
    }

    /**
     * 条件查询学生
     */
    @Override
    public List<Stu> search(Stu stu) {
        stuMapper.create_table_room();//若不存在则新建宿舍表room
        stuMapper.create_table_stu();//若不存在则新建学生表stu
        return stuMapper.search(stu);
    }

    /**
     * 更新学生
     */
    @Override
    public Result update(Stu stu) {
        if (stuMapper.checkbedcount(stu)<=stuMapper.checkpeoplecount(stu))//检查该宿舍是否满人
            return Result.error("count max");
        stuMapper.update(stu);
        return Result.success();
    }

    /**
     * 按照年级/院系优先分配宿舍
     */
    @Override
    public Result assign(String method) {
        if (method.equals("grade")) {
            log.info("按照年级优先分配宿舍");
            return Result.success(assign(stuMapper.grade_mstulist()/*临时男表*/,stuMapper.grade_fstulist()/*临时女表*/));//分配宿舍后返回学生表
        }else if (method.equals("dep")){
            log.info("按照院系优先分配宿舍");
            return Result.success(assign(stuMapper.dep_mstulist()/*临时男表*/,stuMapper.dep_fstulist()/*临时女表*/));//分配宿舍后返回学生表
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

        //将女表全添加到男表，此时男表为分配宿舍后的新学生表
        mstu.addAll(fstu);

        stuMapper.full_delete();//清空stu(旧学生)表
        for(int i=0;i< mstu.size();i++)//将新学生表添加到stu表
            stuMapper.insert(mstu.get(i));
        return mstu;
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
