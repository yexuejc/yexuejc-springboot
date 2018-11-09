package com.yexuejc.springboot.base.security;

import com.yexuejc.base.util.JsonUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 登录参数 request <br/>
 * 用于存放授权后，需要提取的用户信息
 *
 * @ClassName: ConsumerToken
 * @Description: 用于存放授权后，需要提取的用户信息
 * @author: maxf
 * @date: 2017年11月22日 下午4:39:29
 */
public class ConsumerToken extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = -1923797941L;


    /**
     * 消费者用户名（手机号）
     */
    private String username;
    /**
     * 登录方式
     */
    private String logtype;
    /**
     * 短信验证码
     */
    private String smscode;
    /**
     * openid
     */
    private String openid;
    /********************************** 第三方登录时附带信息*************************************/
    /**
     * 头像
     */
    private String head;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String sex;


    /********************************** 第三方登录时附带信息*************************************/

    /**
     * 是否注册账号：减少sms注册时短信校验次数
     */
    public boolean isReg = false;


    public ConsumerToken(String username) {
        super(null, null);
        this.username = username;
    }

    public ConsumerToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public ConsumerToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public ConsumerToken(String logtype, String smscode, String openid, String username, Object credentials, String head, String nickname,
                         String sex) {
        super(username, credentials);
        this.username = username;
        this.logtype = logtype;
        this.smscode = smscode;
        this.openid = openid;
        this.head = head;
        this.nickname = nickname;
        this.sex = sex;
    }


    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
