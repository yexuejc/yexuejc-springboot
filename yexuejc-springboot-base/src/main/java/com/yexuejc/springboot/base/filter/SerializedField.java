package com.yexuejc.springboot.base.filter;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 指定出参加密
 *
 * @version 1.0.5
 * @ClassName: SerializedField
 * @Description:
 * @author: maxf
 * @date: 2018/5/12 22:51
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface SerializedField {
    /**
     * 是否加密
     *
     * @return
     */
    boolean encode() default true;
}