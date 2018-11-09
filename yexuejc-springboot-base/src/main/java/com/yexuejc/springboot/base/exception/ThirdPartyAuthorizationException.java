package com.yexuejc.springboot.base.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 第三方授权异常
 *
 * @author: maxf
 * @date: 2018/5/27 19:20
 */
public class ThirdPartyAuthorizationException extends AuthenticationException {
    /**
     * 错误码
     */
    private String code = "E";

    public ThirdPartyAuthorizationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ThirdPartyAuthorizationException(String msg) {
        super(msg);
    }

    public ThirdPartyAuthorizationException() {
        super("授权异常");
    }

    public ThirdPartyAuthorizationException(Throwable t) {
        super("授权异常", t);
    }


    public ThirdPartyAuthorizationException(String code, String message) {
        super(message);
        this.code = code;
    }
    public ThirdPartyAuthorizationException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }
}
