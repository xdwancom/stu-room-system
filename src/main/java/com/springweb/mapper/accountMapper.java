package com.springweb.mapper;

import com.springweb.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface accountMapper {
    @Select("SELECT * FROM account WHERE username = #{username}")
    Account getByUsername(String username);

    @Insert("INSERT INTO account(username, password, salt) VALUES(#{username}, #{password}, #{salt})")
    void insert(Account account);
}
