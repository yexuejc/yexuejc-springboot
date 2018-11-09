package com.yexuejc.springboot.base.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里OSS相关配置
 *
 * @author maxf
 * @version 1.0
 * @ClassName OssProperties
 * @Description
 * @date 2018/11/1 10:30
 */
@ConfigurationProperties(prefix = "yexuejc.alibaba.oss")
public class OssProperties {

    /**
     * Endpoint 默认内网，外网需要自行配置：http://oss-cn-hangzhou.aliyuncs.com
     */
    private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    /**
     * 产品域名(固定)
     */
    private String accessKeyId = "xxxxx";
    /**
     * 区域ID(固定)
     */
    private String accessKeySecret = "xxxxx";
    /**
     * 默认bucket
     */
    private String bucket = "xxxxx";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
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
