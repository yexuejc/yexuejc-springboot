package com.yexuejc.springboot.base.exception;

/**
 * 网关异常
 *
 * @author: maxf
 * @date: 2018/5/12 18:36
 */
public class GatewayException extends RuntimeException {

    private static final long serialVersionUID = -2390195902982826130L;
    /**
     * 错误码
     */
    private String code = "E";

    public GatewayException() {
        super("数据错误");
    }

    public GatewayException(String message) {
        super(message);
    }
}
