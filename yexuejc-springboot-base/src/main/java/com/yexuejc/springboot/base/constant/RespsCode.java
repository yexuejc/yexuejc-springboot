package com.yexuejc.springboot.base.constant;

/**
 * 返回参数code-msg已经sub_code-sub_msg
 * <p>IA_xxxx:表示子业务CODE 并且网关code为INS_AUTH</p>
 * <p>ACC.xxxx:表示账号相关</p>
 * <p>SYS.xxxx:表示系统（代码）相关</p>
 *
 * @author maxf
 * @version 1.0
 * @ClassName RespsCode
 * @Description
 * @date 2019/1/15 10:34
 */
public enum RespsCode {
    /**网关*******************************************************************************************/
    /**
     * 成功
     */
    SUCCESS("10000", "请求成功"),
    /**
     * 服务不可用
     */
    SRV_ERR("20000", "服务不可用"),
    /**
     * 授权权限不足：有权限,但是有问题，一般包括授权过期，账号异常等
     */
    INS_AUTH("20001", "授权权限不足"),
    /**
     * 缺少必选参数
     */
    MIS_PARAM("40001", "缺少必选参数"),
    /**
     * 业务处理失败,对应业务错误码，明细错误码
     */
    BIZ_ERR("40004", "业务处理失败"),
    /**
     * 权限不足：没有权限
     */
    PERMISSION_DENIED("40006", "权限不足"),
    /**
     * 系统错误
     */
    INTERIOR_BIZ_ERR("99999", "系统错误"),

    /**INS_AUTH*******************************************************************************************/
    /**
     * <pre>
     * 20001:INS_AUTH
     * ACC.IS_LOCK:该账户已被禁用
     * </pre>
     */
    IA_IS_LOCK("ACC.IS_LOCK", "该账户已被禁用"),

    /**
     * <pre>
     * 20001:INS_AUTH
     * ACC.IS_EXPIRE:该帐户已过期
     * </pre>
     */
    IA_IS_EXPIRE("ACC.IS_EXPIRE", "该帐户已过期"),

    /**
     * <pre>
     * 20001:INS_AUTH
     * ACC.LOGIN_IS_EXPIRE:登陆凭证已经过期，请重新登陆
     * </pre>
     */
    IA_LOGIN_IS_EXPIRE("ACC.LOGIN_IS_EXPIRE", "登陆凭证已经过期，请重新登陆"),
    /**
     * <pre>
     * 20001:INS_AUTH
     * ACC.IS_LOCKED:该账户被锁定
     * </pre>
     */
    IA_IS_LOCKED("ACC.IS_LOCKED", "该账户被锁定"),
    /**
     * <pre>
     * 20001:INS_AUTH
     * ACC.CREDENTIALS_NOT_FOUND:身份验证凭证未找到
     * </pre>
     */
    IA_CREDENTIALS_NOT_FOUND("ACC.CREDENTIALS_NOT_FOUND", "身份验证凭证未找到"),

    /**BIZ_ERR*******************************************************************************************/
    /**
     * <pre>
     * 40004:BIZ_ERR
     * ACC.PWD_IS_ERR:密码错误
     * </pre>
     */
    BE_PWD_IS_ERR("ACC.PWD_IS_ERR", "密码错误"),
    /**
     * <pre>
     * 40004:BIZ_ERR
     * ACC.NOT_FOUND:该账户名不存在
     * </pre>
     */
    BE_NOT_FOUND("ACC.NOT_FOUND", "该账户名不存在"),
    /**
     * <pre>
     * 40004:BIZ_ERR
     * SYS.GATEWAY:网关异常
     * </pre>
     */
    BE_GATEWAY("SYS.GATEWAY", "网关异常"),
    /**
     * <pre>
     * 40004:BIZ_ERR
     * BIZ.IMG_FAIL:图片异常
     * </pre>
     */
    BE_IMG_FAIL("BIZ.IMG_FAIL", "图片异常"),
    /**
     * <pre>
     * 40004:BIZ_ERR
     * ACC.LOGIN_AUTH:登录授权异常
     * </pre>
     */
    BE_LOGIN_AUTH("ACC.LOGIN_AUTH", "登录授权异常"),

    /**PERMISSION_DENIED*******************************************************************************************/
    /**
     * <pre>
     * 40006:PERMISSION_DENIED
     * ACC.NOT_LOGIN:未登录时访问需要权限的接口
     * </pre>
     */
    PD_NOT_LOGIN("ACC.NOT_LOGIN", "您尚未登陆"),
    /**
     * <pre>
     * 40006:PERMISSION_DENIED
     * ACC.NOT_AUTH:用户缺少权限
     * </pre>
     */
    PD_NOT_AUTH("ACC.NOT_AUTH", "用户缺少权限"),

    /**INTERIOR_BIZ_ERR*******************************************************************************************/
    /**
     * <pre>
     * 99999:INTERIOR_BIZ_ERR
     * SYS.CONVERT_EXP:类转换异常
     * </pre>
     */
    IBE_CONVERT_EXP("SYS.CONVERT_EXP", "类转换异常");

    /**
     * code
     */
    public String code;
    /**
     * 信息
     */
    public String msg;

    RespsCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
