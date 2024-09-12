package com.springweb.mapper;

import com.springweb.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OperateLogMapper {

    @Update("CREATE TABLE IF NOT EXISTS `operate_log`(\n" +
            "id            int unsigned primary key auto_increment comment 'ID',\n" +
            "operate_user  varchar(30)  null comment '操作人ID',\n" +
            "operate_time  datetime      null comment '操作时间',\n" +
            "class_name    varchar(100)  null comment '操作的类名',\n" +
            "method_name   varchar(100)  null comment '操作的方法名',\n" +
            "method_params varchar(1000) null comment '方法参数',\n" +
            "return_value  varchar(2000) null comment '返回值',\n" +
            "cost_time     bigint        null comment '方法执行耗时, 单位:ms'\n" +
            ")comment '操作日志表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
    void create_table_operate_log();

    //插入日志数据
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void insert(OperateLog log);
}
