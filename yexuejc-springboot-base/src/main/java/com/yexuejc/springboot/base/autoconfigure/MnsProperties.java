package com.yexuejc.springboot.base.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云消息服务MNS相关配置
 *
 * @author maxf
 * @version 1.0
 * @ClassName MnsProperties
 * @Description
 * @date 2018/11/1 10:27
 */
@ConfigurationProperties(prefix = "yexuejc.alibaba.mns")
public class MnsProperties {
    /**
     * 在RAM创建的AccessKey
     */
    private String accessKeyId="xxxxxxxxxx";
    /**
     * 在RAM创建的SecretKey
     */
    private String accessKeySecret="xxxxxxxxx";
    /**
     * MNS接入endpoint，公网，私网，VPC各不相同，需要分环境配置
     */
    private String endpoint="http://00000000.mns.cn-hangzhou.aliyuncs.com";

    /**
     * 队列名称前缀（消费端为CONSUMER，测试环境为IVT-CONSUMER）
     */
    private String queueNamePrefix;
    private String topicName = "IVT";

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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getQueueNamePrefix() {
        return queueNamePrefix;
    }

    public void setQueueNamePrefix(String queueNamePrefix) {
        this.queueNamePrefix = queueNamePrefix;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
