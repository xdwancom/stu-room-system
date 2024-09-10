package com.springweb.mapper;
import com.springweb.pojo.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface roomMapper {

    /**
     * 批量删除宿舍
     * @param ids
     */
    void updateroomid(List<String> ids);//清空住在该宿舍学生的unit，roomid
    void deleteid(List<String> ids);//删除宿舍并清空住在该宿舍学生的roommsg

    /**
     * 新增宿舍
     * @param room
     */
    @Insert("insert into room(unit,roomid, floor, bedcount, money,roommsg) values(#{unit},#{roomid},#{floor},#{bedcount},#{money},concat(#{unit},'-',#{roomid}))")
    void insert(Room room);

    /**
     * 查询指定宿舍信息是否存在
     * @return
     */
    @Select("SELECT count(roommsg) FROM room WHERE roommsg =concat(#{unit},'-',#{roomid}) LIMIT 1")//加上limit 1查到一条则停止
    boolean room_count(Room room);

    /**
     * 条件查询宿舍
     * @param room
     * @return
     */
    List<Room> search(Room room);

    /**
     * 更新指定宿舍
     * @param room
     * @return
     */
    @Update("update room set bedcount = #{bedcount},money = #{money} where roommsg = #{roommsg}")
    void update(Room room);
}
