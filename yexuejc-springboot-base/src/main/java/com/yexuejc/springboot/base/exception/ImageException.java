
package com.yexuejc.springboot.base.exception;

public class ImageException extends RuntimeException {
    private static final long serialVersionUID = -2390195902982826130L;
    private String code = "E";

    public ImageException() {
        super("图片异常");
    }

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String msg, Throwable t) {
        super(msg, t);
    }

    public ImageException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }
}
