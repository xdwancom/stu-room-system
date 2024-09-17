package com.springweb.controller;

import com.springweb.annotation.Log;
import com.springweb.pojo.AccountDTO;
import com.springweb.pojo.Result;
import com.springweb.service.accountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录信息Controller
 */
@Slf4j
@Log
@CrossOrigin(origins = {"*", "null"})
@RestController//请求处理类,自动将方法返回值转换为json响应返回
public class accountController {

    @Autowired//ioc容器管理，依赖注入
    private accountService accountService;

    @PostMapping("/login")
    public Result login(@RequestBody AccountDTO accountDTO){
        return accountService.login(accountDTO);
    }
}
