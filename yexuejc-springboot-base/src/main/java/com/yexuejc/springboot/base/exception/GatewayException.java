package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 网关异常 : 包含网关加解密，校验等
 * <p>BIZ_ERR("40004", "业务处理失败")</p>
 * <p>BE_GATEWAY("SYS.GATEWAY", "网关异常")</p>
 *
 * @author: maxf
 * @date: 2018/5/12 18:36
 */
public class GatewayException extends BasicException {

    private static final long serialVersionUID = -2390195902982826130L;

    {
        this.code = RespsCode.BIZ_ERR;
        this.respsSubCode = RespsCode.BE_GATEWAY;
    }

    public GatewayException() {
        super();
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(String subCode, String message) {
        super(subCode, message);
    }

    public GatewayException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public GatewayException(Throwable cause) {
        super(cause);
    }

    protected GatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
