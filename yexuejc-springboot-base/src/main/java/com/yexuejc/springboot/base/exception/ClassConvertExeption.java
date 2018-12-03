package com.yexuejc.springboot.base.exception;

/**
 * 类型转换异常
 *
 * @author: maxf
 * @date: 2018/5/12 18:36
 */
public class ClassConvertExeption extends RuntimeException {

    private static final long serialVersionUID = -2390195902982826130L;
    /**
     * 错误码
     */
    private String code = "E";

    public ClassConvertExeption() {
        super("类型转换异常");
    }

    public ClassConvertExeption(String message) {
        super(message);
    }
}
