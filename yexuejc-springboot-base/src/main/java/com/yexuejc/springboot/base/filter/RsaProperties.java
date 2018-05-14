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
     * 公钥
     */
    private String publicKey = "";
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
