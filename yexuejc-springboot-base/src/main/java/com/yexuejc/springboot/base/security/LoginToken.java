package com.yexuejc.springboot.base.security;

import com.yexuejc.base.util.JsonUtil;

import java.io.Serializable;

/**
 * 封装登录信息至JWT得到token
 *
 * @author: maxf
 * @date: 2018/5/31 21:34
 */
public class LoginToken implements Serializable {
    /**
     * 消费者用户名（手机号）
     */
    private String username;

    public LoginToken() {
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public LoginToken(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
