
package com.yexuejc.springboot.base.exception;

import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 图片异常
 * <p>BIZ_ERR("40004", "业务处理失败")</p>
 * <p>BE_IMG_FAIL("BIZ.IMG_FAIL", "图片异常")</p>
 *
 * @author maxf
 * @version 1.0
 * @ClassName ImageException
 * @Description
 * @date 2019/1/15 13:54
 */
public class ImageException extends BasicException {
    private static final long serialVersionUID = -2390195902982826130L;

    {
        this.code = RespsCode.BIZ_ERR;
        this.respsSubCode = RespsCode.BE_IMG_FAIL;
    }

    public ImageException() {
        super();
    }

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageException(String subCode, String message) {
        super(subCode, message);
    }

    public ImageException(String subCode, String message, Throwable cause) {
        super(subCode, message, cause);
    }

    public ImageException(Throwable cause) {
        super(cause);
    }

    protected ImageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
