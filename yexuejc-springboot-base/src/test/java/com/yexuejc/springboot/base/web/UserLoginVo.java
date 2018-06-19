package com.yexuejc.springboot.base.web;

import java.io.Serializable;

/**
 * @author: maxf
 * @date: 2018/6/15 22:09
 */
public class UserLoginVo implements Serializable {
    private String phoneNumber;
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}