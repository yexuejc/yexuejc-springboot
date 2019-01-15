package com.yexuejc.springboot.base.http;

import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.springboot.base.constant.RespsCode;

/**
 * 输出加密结果
 *
 * @author maxf
 * @version 1.0
 * @ClassName ResponseParams
 * @Description
 * @date 2018/12/20 16:05
 */
public class ResponseParams {
    /**
     * 消息
     */
    private String msg;
    /**
     * 业务code
     */
    private String code;
    /**
     * 详细业务code
     */
    private String subCode;
    /**
     * 详细业务msg
     */
    private String subMsg;
    /**
     * 加密业务内容
     */
    private Object content;
    /**
     * 签名
     */
    private String sign;
    /**
     * 秒级时间戳
     */
    private Long timestemp;

    public ResponseParams() {
    }

    public ResponseParams(RespsCode respsCode) {
        this.code = respsCode.code;
        this.msg = respsCode.msg;
    }

    /**
     * 直接new 一个 网关code msg
     *
     * @param code 网关code
     * @param msg  网关msg
     * @return
     */
    public ResponseParams(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 网关（code,msg） 带 content
     *
     * @param respsCode 网关（code,msg）
     * @param content   网关返回内容
     */
    public ResponseParams(RespsCode respsCode, Object content) {
        this.code = respsCode.code;
        this.msg = respsCode.msg;
        this.content = content;
    }

    /**
     * 直接new 一个 网关code msg
     *
     * @param code 网关code
     * @param msg  网关msg
     * @return
     */
    public static ResponseParams resps(String code, String msg) {
        return new ResponseParams(code, msg);
    }

    /**
     * 直接new 一个 网关code msg
     *
     * @param respsCode 网关（code,msg）
     * @return
     */
    public static ResponseParams resps(RespsCode respsCode) {
        return new ResponseParams(respsCode);
    }

    /**
     * 网关（code,msg） 带 content
     *
     * @param respsCode 网关（code,msg）
     * @param content   网关返回内容
     */
    public static ResponseParams resps(RespsCode respsCode, Object content) {
        return new ResponseParams(respsCode, content);
    }

    /**
     * 网关（code,msg）+ 业务（sub_code,sub_msg）
     *
     * @param respsCode 网关（code,msg）
     * @param subCode   业务（sub_code,sub_msg）
     * @return
     */
    public static ResponseParams resps(RespsCode respsCode, RespsCode subCode) {
        ResponseParams responseParams = new ResponseParams(respsCode);
        responseParams.setSub(subCode);
        return responseParams;
    }

    /**
     * 网关（code,msg）+ 业务（sub_code,sub_msg）+ content
     *
     * @param respsCode 网关（code,msg）
     * @param subCode   业务（sub_code,sub_msg）
     * @param content   网关返回内容
     * @return
     */
    public static ResponseParams resps(RespsCode respsCode, RespsCode subCode, Object content) {
        ResponseParams responseParams = new ResponseParams(respsCode, content);
        responseParams.setSub(subCode);
        return responseParams;
    }

    /**
     * 操作成功
     * <p>code: SUCCESS("10000", "请求成功")</p>
     *
     * @return
     */
    public static ResponseParams succ() {
        ResponseParams responseParams = new ResponseParams(RespsCode.SUCCESS);
        return responseParams;
    }

    /**
     * 业务错误
     * <p>code: BIZ_ERR("40004", "业务处理失败")</p>
     *
     * @param subCode 业务 sub_code
     * @param subMsg  业务 sub_msg
     * @return
     */
    public static ResponseParams fail(String subCode, String subMsg) {
        ResponseParams responseParams = new ResponseParams(RespsCode.BIZ_ERR);
        responseParams.setSub(subCode, subMsg);
        return responseParams;
    }

    /**
     * 系统错误
     * <p>code: INTERIOR_BIZ_ERR("99999", "系统错误")</p>
     *
     * @param subCode 业务 sub_code
     * @param subMsg  业务 sub_msg
     * @return
     */
    public static ResponseParams err(String subCode, String subMsg) {
        ResponseParams responseParams = new ResponseParams(RespsCode.INTERIOR_BIZ_ERR);
        responseParams.setSub(subCode, subMsg);
        return responseParams;
    }

    public ResponseParams setSub(RespsCode respsCode) {
        this.subCode = respsCode.code;
        this.subMsg = respsCode.msg;
        return this;
    }

    public ResponseParams setSub(String subCode, String subMsg) {
        this.subCode = subCode;
        this.subMsg = subMsg;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public String getSubCode() {
        return subCode;
    }

    public ResponseParams setSubCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public ResponseParams setSubMsg(String subMsg) {
        this.subMsg = subMsg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseParams setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseParams setCode(String code) {
        this.code = code;
        return this;
    }

    public ResponseParams setCode(RespsCode code) {
        this.code = code.code;
        this.msg = code.msg;
        return this;
    }

    public Object getContent() {
        return content;
    }

    public ResponseParams setContent(Object content) {
        this.content = content;
        return this;
    }

    public Long getTimestemp() {
        return timestemp;
    }

    public ResponseParams setTimestemp(Long timestemp) {
        this.timestemp = timestemp;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public ResponseParams setSign(String sign) {
        this.sign = sign;
        return this;
    }

}
