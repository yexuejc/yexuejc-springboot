package com.yexuejc.springboot.base.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "yexuejc.oss")
public class OssProperties {

    /** Endpoint 默认内网，外网需要自行配置：http://oss-cn-hangzhou.aliyuncs.com */
    private String endpoint = "oss-cn-hangzhou-internal.aliyuncs.com";
    /** 产品域名(固定) */
    private String accessKeyID = "LTAInCYwtsprAu8g";
    /** 区域ID(固定) */
    private String accessKeySecret = "6aqMtyFuJPUPChYpZSLsQ11cg4qby7";
    /** 默认bucket */
    private String bucket = "ecentm-res";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

}
