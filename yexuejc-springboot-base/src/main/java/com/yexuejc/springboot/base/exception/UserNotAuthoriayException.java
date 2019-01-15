package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 用户无权限
 * <p>PERMISSION_DENIED("40006", "权限不足")</p>
 *
 * @author maxf
 * @version 1.0
 * @ClassName UserNotAuthoriayException
 * @Description
 * @date 2018/11/20 20:13
 */
public class UserNotAuthoriayException extends BasicException {
    private static final long serialVersionUID = 7752594158082817319L;

    {
        this.code = RespsCode.PERMISSION_DENIED;
        this.respsSubCode = RespsCode.PD_NOT_AUTH;
    }

    public UserNotAuthoriayException() {
        super();
    }

    public UserNotAuthoriayException(String message) {
        super(message);
    }

    public UserNotAuthoriayException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuthoriayException(String subCode, String message) {
        super(subCode, message);
    }

    public UserNotAuthoriayException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public UserNotAuthoriayException(Throwable cause) {
        super(cause);
    }

    protected UserNotAuthoriayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
