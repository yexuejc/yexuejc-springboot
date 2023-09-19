package com.example.demo.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.mapper.handler.JsonTypeHandler;
import com.yexuejc.base.util.JsonUtil;
import com.yexuejc.springboot.base.security.inte.User;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author yexuejc
 * @since 2018-05-27
 */
@TableName(resultMap = "BaseResultMap")
public class Consumer extends Model<Consumer> implements User {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "consumer_id", type = IdType.ASSIGN_UUID)
    private String consumerId;
    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 密码：md5
     */
    @TableField("pwd")
    private String pwd;
    /**
     * 账号是否启用
     */
    @TableField("is_enable")
    private boolean enable;
    /**
     * 账号是否没有过期
     */
    @TableField("is_non_expire")
    private boolean nonExpire;
    /**
     * 账号是否没有被锁定
     */
    @TableField("is_non_lock")
    private boolean nonLock;
    /**
     * 微信id
     */
    @TableField("wechat_id")
    private String wechatId;
    /**
     * qq id
     */
    @TableField("qq_id")
    private String qqId;
    /**
     * 微博id
     */
    @TableField("weibo_id")
    private String weiboId;
    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;
    /**
     * 用户头像
     */
    @TableField("head")
    private String head;
    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 用户姓别 '男'，‘女’
     */
    @TableField("sex")
    private String sex;
    /**
     * 角色、权限
     */
    @TableField(value = "roles", typeHandler = JsonTypeHandler.class)
    private List<String> roles;
    /**
     * 支付密码
     */
    @TableField("pay_pwd")
    private String payPwd;
    /**
     * 注册方式
     */
    @TableField("reg_type")
    private String regType;
    /**
     * 第三方源头像路径
     */
    @TableField("source_head")
    private String sourceHead;

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public String getConsumerId() {
        return consumerId;
    }

    public Consumer setConsumerId(String consumerId) {
        this.consumerId = consumerId;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Consumer setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean getEnable() {
        return this.enable;
    }

    @Override
    public boolean getNonExpire() {
        return this.nonExpire;
    }

    @Override
    public boolean getNonLock() {
        return this.nonLock;
    }

    public Consumer setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public Consumer setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public Consumer setNonExpire(boolean nonExpire) {
        this.nonExpire = nonExpire;
        return this;
    }

    public Consumer setNonLock(boolean nonLock) {
        this.nonLock = nonLock;
        return this;
    }

    public String getWechatId() {
        return wechatId;
    }

    public Consumer setWechatId(String wechatId) {
        this.wechatId = wechatId;
        return this;
    }

    public String getQqId() {
        return qqId;
    }

    public Consumer setQqId(String qqId) {
        this.qqId = qqId;
        return this;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public Consumer setWeiboId(String weiboId) {
        this.weiboId = weiboId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Consumer setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getHead() {
        return head;
    }

    public Consumer setHead(String head) {
        this.head = head;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Consumer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Consumer setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Consumer setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public Consumer setPayPwd(String payPwd) {
        this.payPwd = payPwd;
        return this;
    }

    public String getRegType() {
        return regType;
    }

    public Consumer setRegType(String regType) {
        this.regType = regType;
        return this;
    }

    public String getSourceHead() {
        return sourceHead;
    }

    public Consumer setSourceHead(String sourceHead) {
        this.sourceHead = sourceHead;
        return this;
    }

    @Override
    public Serializable pkVal() {
        return this.consumerId;
    }
}
