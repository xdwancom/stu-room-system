package com.springweb.service.impl;

import com.springweb.mapper.roomMapper;
import com.springweb.pojo.Result;
import com.springweb.pojo.Room;
import com.springweb.service.roomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)//自动事务管理,默认只有RuntimeException异常才回滚，设置rollbackFor可指定何种异常回滚，此处设置为全部
public class roomServiceImpl implements roomService {
    @Autowired
    private roomMapper roomMapper;

    /**
     * 批量删除宿舍
     * @param ids
     */
    @Override
    public void deleteid(List<String> ids) {
        roomMapper.updateroomid(ids);//清空住在该宿舍学生的unit，roomid
        roomMapper.deleteid(ids);//删除宿舍并清空住在该宿舍学生的roommsg
    }

    /**
     * 新增宿舍
     * @param room
     */
    @Override
    public Result add(Room room) {
        if (roomMapper.room_count(room)) {//检查该宿舍是否存在
            log.info("该宿舍已存在");
            return Result.error("room repeat");
        }
        roomMapper.insert(room);
        return Result.success();
    }

    /**
     * 条件查询宿舍
     * @param room
     */
    @Override
    public List<Room> search(Room room) {
        return roomMapper.search(room);
    }

    /**
     * 更新宿舍
     */
    @Override
    public void update(Room room) {
        roomMapper.update(room);
    }


}
