package com.springweb.service;
import com.springweb.pojo.Account;
import com.springweb.pojo.Result;


/**
 * 登录管理
 */
public interface accountService {
    Result login(Account account);
}
