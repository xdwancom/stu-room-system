package com.springweb.service.impl;

import com.springweb.mapper.accountMapper;
import com.springweb.pojo.Account;
import com.springweb.pojo.AccountDTO;
import com.springweb.pojo.Result;
import com.springweb.service.accountService;
import com.springweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)//自动事务管理,默认只有RuntimeException异常才回滚，设置rollbackFor可指定何种异常回滚，此处设置为全部
public class accountServiceImpl implements accountService {
    @Autowired//ioc容器管理，依赖注入
    private accountMapper accountMapper;

    /**
     * 登录
     */
    @Override
    public Result login(AccountDTO accountDTO) {
        Account account=accountMapper.getByUsername(accountDTO.getUsername());
        if (account == null)
            return Result.error("账号不存在");

        String password = DigestUtils.sha256Hex(accountDTO.getPassword()+account.getSalt());//明文密码+盐值hash加密
        if (password.equals(account.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", account.getUsername());
            claims.put("password", account.getPassword());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        return Result.error("账号密码错误");
    }

    /**
     * 注册
     */
    @Override
    public Result register(AccountDTO accountDTO) {
        String salt=RandomStringUtils.randomAlphanumeric(8);//随机生成8位字符串
        String password = DigestUtils.sha256Hex(accountDTO.getPassword()+salt);//明文密码+盐值hash加密
        try {
            accountMapper.insert(new Account(accountDTO.getUsername(),password,salt));
        }catch (Exception e){
            return Result.error("用户已存在");
        }
        return Result.success();
    }
}
