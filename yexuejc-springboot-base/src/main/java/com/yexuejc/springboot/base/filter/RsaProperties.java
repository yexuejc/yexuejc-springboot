package com.yexuejc.springboot.base.filter;

import com.yexuejc.base.util.JsonUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加密，解密相关配置
 *
 * @version 1.0.5
 * @author: maxf
 * @date: 2018/5/12 18:14
 */
@ConfigurationProperties(prefix = "yexuejc.http.encrypt")
public class RsaProperties {
    /**
     * 私钥
     */
    private String privateKey = "";
    /**
     * 私钥证书路径：默认private.keystore
     */
    private String privateKeyPath = "/private.keystore";
    /**
     * 私钥证书别名
     */
    private String privateAlias = "alias";
    /**
     * 私钥证书密码
     */
    private String privatePwd = "password";
    /**
     * 公钥
     */
    private String publicKey = "";
    /**
     * 公钥证书路径：默认public.cer
     */
    private String publicKeyPath = "/public.cer";

    /**
     * 是否解密
     */
    private boolean decrypt = false;
    /**
     * 是否加密
     */
    private boolean encrypt = false;

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }

    public boolean isDecrypt() {
        return decrypt;
    }

    public void setDecrypt(boolean decrypt) {
        this.decrypt = decrypt;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getPrivateAlias() {
        return privateAlias;
    }

    public void setPrivateAlias(String privateAlias) {
        this.privateAlias = privateAlias;
    }

    public String getPrivatePwd() {
        return privatePwd;
    }

    public void setPrivatePwd(String privatePwd) {
        this.privatePwd = privatePwd;
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
