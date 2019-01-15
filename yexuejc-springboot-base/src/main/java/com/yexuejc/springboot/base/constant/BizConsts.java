package com.yexuejc.springboot.base.constant;

/**
 * 常用字段
 *
 * @author: maxf
 * @date: 2018/3/13 19:43
 */
public class BizConsts {
    /**
     * 用户登录发送短信验证码
     */
    public static String CONSUMER_LOGIN_SMS = "consumer.login.sendSms";
    /**
     * 用户绑定手机号
     */
    public static String CONSUMER_BIND_MOBILE = "consumer.bind.mobile.sms";
    /**
     * 用户登录信息
     */
    public static String CONSUMER_LOGIN_REDIS = "consumer.login.redis.session";

    /**
     * 后台管理员登录信息
     */
    public static String ADMIN_LOGIN_REDIS = "admin.login.redis.session";
}
