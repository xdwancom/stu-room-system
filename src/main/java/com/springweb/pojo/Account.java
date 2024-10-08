package com.springweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账号实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String username; //用户名
    private String password; //密码
    private String salt;
}
