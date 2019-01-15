package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 异常基类
 *
 * <p>1.里面必须实现网关异常消息</p>
 * <p>2.业务消息先取subCode，取不到会取respsSubCode</p>
 * <p>3.设置message为消息，自定义message优先获取，取不到会取respsSubCode带的message</p>
 * <p>4.respsSubCode和subCode都不存在，即没有sub_code(如果有message会替换网关message)</p>
 *
 * @author maxf
 * @version 1.0
 * @ClassName BasicException
 * @Description
 * @date 2019/1/15 11:52
 */
public abstract class BasicException extends RuntimeException {
    private static final long serialVersionUID = 8720020350158032819L;
    /**
     * 网关错误
     */
    protected RespsCode code;
    /**
     * 业务错误
     */
    protected RespsCode respsSubCode;
    /**
     * 自定义业务code
     * <p>可以在respsSubCode中定义枚举，也可以直接传入subCode，message会获取异常message</p>
     */
    protected String subCode;


    public BasicException setSubCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public BasicException() {
        super();
    }

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException(String subCode, String message) {
        super(message);
        this.subCode = subCode;
    }

    public BasicException(String subCode, String message, Throwable cause) {
        super(message, cause);
        this.subCode = subCode;
    }

    public BasicException(Throwable cause) {
        super(cause);
    }

    protected BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
