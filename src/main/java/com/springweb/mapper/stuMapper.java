package com.springweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springweb.pojo.Room;
import com.springweb.pojo.Stu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface stuMapper extends BaseMapper<Stu> {
    /**
     * 查询指定宿舍床位数量
     */
    @Select("select bedcount from room where roommsg=#{roommsg}")
    Integer checkbedcount(Stu stu);

    /**
     * 查询男宿舍信息
     * @return
     */
    @Select("select * from room where MOD(unit, 2)=1 order by roommsg")
    List<Room> mroomlist();
    /**
     * 查询女宿舍信息
     * @return
     */
    @Select("select * from room where MOD(unit, 2)=0 order by roommsg")
    List<Room> froomlist();
}