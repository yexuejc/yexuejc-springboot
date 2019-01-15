package com.yexuejc.springboot.base.exception;

import static com.yexuejc.springboot.base.constant.RespsCode.MIS_PARAM;

/**
 * 参数错误
 *
 * @author maxf
 * @version 1.0
 * @ClassName ParamsException
 * @Description
 * @date 2019/1/15 15:44
 */
public class ParamsException extends BasicException {
    private static final long serialVersionUID = 4645399726493530751L;

    {
        this.code = MIS_PARAM;
    }

    public ParamsException() {
        super();
    }

    public ParamsException(String message) {
        super(message);
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsException(String subCode, String message) {
        super(subCode, message);
    }

    public ParamsException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public ParamsException(Throwable cause) {
        super(cause);
    }

    protected ParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
