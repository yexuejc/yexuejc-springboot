package com.yexuejc.springboot.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志处理类
 */
public class LogUtil {
    private LogUtil() {
    }

    /**
     * 用于记录访问日志，输出到access.log
     */
    public final static Logger accessLogger = LoggerFactory.getLogger("com.yexuejc.uselaw.access");
    /**
     * 用于记录业务日志，输出到biz.log
     */
    public final static Logger bizLogger = LoggerFactory.getLogger("com.yexuejc.uselaw.biz");
    /**
     * 用于记录程序异常日志，输出到exception.log
     */
    public final static Logger exceptionLogger = LoggerFactory.getLogger("com.yexuejc.uselaw.exception");

    /**
     * 格式化日志消息（将;替换为_)
     *
     * @param msg 需要被格式化的字符串
     * @return 当传入参数为null时，返回null
     */
    public final static String format(String msg) {
        if (msg == null) {
            return null;
        }
        return msg.replaceAll(";", "_");
    }
}
