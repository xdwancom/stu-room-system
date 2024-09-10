package com.springweb.mapper;
import com.springweb.pojo.Room;
import com.springweb.pojo.Stu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface stuMapper {

    @Update("CREATE TABLE IF NOT EXISTS `room` (\n" +
            "                        `unit` int NOT NULL COMMENT '楼栋单元',\n" +
            "                        `roomid` int NOT NULL COMMENT '宿舍号',\n" +
            "                        `floor` int NOT NULL COMMENT '楼层',\n" +
            "                        `bedcount` int NOT NULL COMMENT '床位数',\n" +
            "                        `money` int NOT NULL COMMENT '住宿费',\n" +
            "                        `roommsg` varchar(20) NOT NULL COMMENT '楼栋-宿舍号',\n" +
            "                        PRIMARY KEY (`roommsg`),\n" +
            "                        UNIQUE KEY `room_roommsg_uindex` (`roommsg`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void create_table_room();

    @Update("CREATE TABLE  IF NOT EXISTS `stu` (\n" +
            "                       `unit` int DEFAULT NULL COMMENT '楼栋单元',\n" +
            "                       `roomid` int DEFAULT NULL COMMENT '宿舍号',\n" +
            "                       `id` int NOT NULL AUTO_INCREMENT COMMENT '学号',\n" +
            "                       `name` varchar(20) NOT NULL COMMENT '姓名',\n" +
            "                       `gender` varchar(20) NOT NULL COMMENT '性别',\n" +
            "                       `age` int NOT NULL COMMENT '年龄',\n" +
            "                       `department` varchar(20) NOT NULL COMMENT '院系',\n" +
            "                       `grade` int NOT NULL COMMENT '年级',\n" +
            "                       `phone` varchar(20) NOT NULL COMMENT '电话',\n" +
            "                       `roommsg` varchar(20) DEFAULT NULL COMMENT '楼栋-宿舍号',\n" +
            "                       PRIMARY KEY (`id`),\n" +
            "                       UNIQUE KEY `stu_id_uindex` (`id`),\n" +
            "                       KEY `stu_room_roommsg_fk` (`roommsg`),\n" +
            "                       CONSTRAINT `stu_room_roommsg_fk` FOREIGN KEY (`roommsg`) REFERENCES `room` (`roommsg`) ON DELETE SET NULL ON UPDATE CASCADE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void create_table_stu();

    /**
     * 查询指定学号学生信息是否存在
     * @return
     */
    @Select("select count(id) from stu where id = #{id} limit 1")//加上limit 1查到一条则停止
    boolean id_count(Integer id);

    /**
     * 批量删除学生
     * @param ids
     */
    void deleteid(List<Integer> ids);

    /**
     * 新增学生
     * @param stu
     */
    @Insert("insert into stu(unit,roomid,id,name, gender, age, department, grade, phone,roommsg) values(#{unit},#{roomid},#{id},#{name},#{gender},#{age},#{department},#{grade},#{phone},concat(#{unit},'-',#{roomid}))")
    void insert(Stu stu);

    /**
     * 条件查询学生
     * @param stu
     * @return
     */
    List<Stu> search(Stu stu);

    /**
     * 更新指定学生
     * @param stu
     * @return
     */
    @Update("update stu set unit=#{unit},roomid=#{roomid},name=#{name},gender= #{gender},age= #{age},department = #{department},grade = #{grade} ,phone = #{phone},roommsg=concat(#{unit},'-',#{roomid}) where id = #{id}")
    void update(Stu stu);

    /**
     * 查询指定宿舍床位数量
     */
    @Select("select bedcount from room where unit=#{unit} and roomid=#{roomid}")
    Integer checkbedcount(Stu stu);

    /**
     * 查询指定宿舍除自己以外的已住人数
     */
    @Select("select count(*) from stu where unit=#{unit} and roomid=#{roomid} and id!=#{id}")
    Integer checkpeoplecount(Stu stu);


    /**
     * 年级优先排序查询男学生信息
     * @return
     */
    @Select("select * from stu where gender='男' order by grade,department,id")
    List<Stu> grade_mstulist();
    /**
     * 年级优先排序查询女学生信息
     * @return
     */
    @Select("select * from stu where gender='女' order by grade,department,id")
    List<Stu> grade_fstulist();
    /**
     * 学院优先排序查询男学生信息
     * @return
     */
    @Select("select * from stu where gender='男' order by department,grade,id")
    List<Stu> dep_mstulist();
    /**
     * 学院优先排序查询女学生信息
     * @return
     */
    @Select("select * from stu where gender='女' order by department,grade,id")
    List<Stu> dep_fstulist();

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
    /**
     * 删除全部学生
     * @return
     */
    @Delete("delete from stu")
    void full_delete();
}