package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 第三方授权异常
 * <p>BIZ_ERR("40004", "业务处理失败")</p>
 * <p>BE_LOGIN_AUTH("ACC.LOGIN_AUTH", "登录授权异常")</p>
 *
 * @author: maxf
 * @date: 2018/5/27 19:20
 */
public class ThirdPartyAuthorizationException extends BasicException {
    {
        this.code = RespsCode.BIZ_ERR;
        this.respsSubCode = RespsCode.BE_LOGIN_AUTH;
    }

    public ThirdPartyAuthorizationException() {
        super();
    }

    public ThirdPartyAuthorizationException(String message) {
        super(message);
    }

    public ThirdPartyAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThirdPartyAuthorizationException(String subCode, String message) {
        super(subCode, message);
    }

    public ThirdPartyAuthorizationException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public ThirdPartyAuthorizationException(Throwable cause) {
        super(cause);
    }

    protected ThirdPartyAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
