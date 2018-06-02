package com.yexuejc.springboot.base.web;

import com.yexuejc.base.pojo.PagerVO;

/**
 * @author: maxf
 * @date: 2018/6/2 12:06
 */
public class TaVO extends PagerVO {

    /**
     * ret : 0
     * ExpireTime : 2015/10/28 23:59:59
     * rettxt : OK
     * Token : 69296128A59798E2D423D3B1A9F766F4
     */

    private String ret;
    private String expireTime;
    private String rettxt;
    private String token;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRettxt() {
        return rettxt;
    }

    public void setRettxt(String rettxt) {
        this.rettxt = rettxt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}