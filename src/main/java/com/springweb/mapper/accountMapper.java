package com.springweb.mapper;

import com.springweb.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface accountMapper {
    @Select("select count(*) from account where username = #{username} and password = #{password} limit 1")//加上limit 1查到一条则停止
    boolean login(Account account);
}
