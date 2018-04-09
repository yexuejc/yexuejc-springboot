package com.yexuejc.springboot.base.constant;

/**
 * <pre>
 * 名称：项目+业务code
 * 业务code:项目编号+业务编号
 *      base:100
 *      api:101
 *      wap:102
 *      admin:103
 * </pre>
 *
 * @author: maxf
 * @date: 2018/3/13 19:43
 */
public class BizConsts {
    /**
     * base_code:100
     */
    public static String BASE_CODE = "100";

    /**
     * 100001:该账户已被禁用
     */
    public static String BASE_IS_LOCK_CODE = BASE_CODE + "001";
    public static String BASE_IS_LOCK_MSG = "该账户已被禁用";
    /**
     * 100002:该帐户已过期
     */
    public static String BASE_IS_EXPIRE_CODE = BASE_CODE + "002";
    public static String BASE_IS_EXPIRE_MSG = "该帐户已过期";
    /**
     * 100003:登陆凭证已经过期，请重新登陆
     */
    public static String BASE_LOGIN_IS_EXPIRE_CODE = BASE_CODE + "003";
    public static String BASE_LOGIN_IS_EXPIRE_MSG = "登陆凭证已经过期，请重新登陆";
    /**
     * 100004:该账户被锁定
     */
    public static String BASE_IS_LOCKED_CODE = BASE_CODE + "004";
    public static String BASE_IS_LOCKED_MSG = "该账户被锁定";
    /**
     * 100005:身份验证凭证未找到
     */
    public static String BASE_CREDENTIALS_NOT_FOUND_CODE = BASE_CODE + "005";
    public static String BASE_CREDENTIALS_NOT_FOUND_MSG = "身份验证凭证未找到";
    /**
     * 100006:密码错误
     */
    public static String BASE_PWD_IS_ERR_CODE = BASE_CODE + "006";
    public static String BASE_PWD_IS_ERR_MSG = "密码错误";
    /**
     * 100007:该账户名不存在
     */
    public static String BASE_ACCOUNT_NOT_FOUND_CODE = BASE_CODE + "007";
    public static String BASE_ACCOUNT_NOT_FOUND_MSG = "该账户名不存在";
    /**
     * 100008:登录时发生错误，请联系系统管理员
     */
    public static String BASE_SYS_ERR_CODE = BASE_CODE + "008";
    public static String BASE_SYS_ERR_MSG = "登录时发生错误，请联系系统管理员";
    /**
     * 100009:权限不足
     */
    public static String BASE_NOT_ROLE_CODE = BASE_CODE + "009";
    public static String BASE_NOT_ROLE_MSG = "您无权访问该资源";
    /**
     * 100010:尚未登陆
     */
    public static String BASE_NOT_LOGIN_CODE = BASE_CODE + "010";
    public static String BASE_NOT_LOGIN_MSG = "您尚未登陆";
}
