package com.yexuejc.springboot.base.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户无权限
 *
 * @author maxf
 * @version 1.0
 * @ClassName UserNotAuthoriayException
 * @Description
 * @date 2018/11/20 20:13
 */
public class UserNotAuthoriayException extends AuthenticationException {
    private static final long serialVersionUID = 7752594158082817319L;

    public UserNotAuthoriayException() {
        super("用户缺少权限");
    }

    public UserNotAuthoriayException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotAuthoriayException(String msg) {
        super(msg);
    }
}
