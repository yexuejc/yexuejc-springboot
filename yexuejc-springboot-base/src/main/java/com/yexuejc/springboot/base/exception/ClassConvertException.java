package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 类型转换异常
 * <p>INTERIOR_BIZ_ERR("99999", "系统错误")</p>
 * <p>IBE_CONVERT_EXP("SYS.CONVERT_EXP", "类转换异常")</p>
 *
 * @author: maxf
 * @date: 2018/5/12 18:36
 */
public class ClassConvertException extends BasicException {

    private static final long serialVersionUID = -2390195902982826130L;

    {
        this.code = RespsCode.INTERIOR_BIZ_ERR;
        this.respsSubCode = RespsCode.IBE_CONVERT_EXP;
    }

    public ClassConvertException() {
        super();
    }

    public ClassConvertException(String message) {
        super(message);
    }

    public ClassConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassConvertException(String subCode, String message) {
        super(subCode, message);
    }

    public ClassConvertException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public ClassConvertException(Throwable cause) {
        super(cause);
    }

    protected ClassConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
