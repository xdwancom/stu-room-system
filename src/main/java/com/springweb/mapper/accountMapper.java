package com.springweb.mapper;

import com.springweb.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface accountMapper {
    @Select("SELECT * FROM account WHERE username = #{username}")
    Account getByUsername(String username);
}
