package com.springweb.mapper;

import com.springweb.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface accountMapper {
    @Select("SELECT EXISTS (SELECT 1 FROM account WHERE username = #{username} AND password = #{password})")
    boolean login(Account account);
}
