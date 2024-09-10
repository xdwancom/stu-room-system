package com.springweb.controller;

import com.springweb.pojo.Account;
import com.springweb.pojo.Result;
import com.springweb.service.accountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录信息Controller
 */
@Slf4j
@CrossOrigin(origins = {"*", "null"})
@RestController//请求处理类,自动将方法返回值转换为json响应返回
public class accountController {

    @Autowired//ioc容器管理，依赖注入
    private accountService accountService;

    @PostMapping("/login")
    public Result login(@RequestBody Account account){
        return accountService.login(account);
    }
}
