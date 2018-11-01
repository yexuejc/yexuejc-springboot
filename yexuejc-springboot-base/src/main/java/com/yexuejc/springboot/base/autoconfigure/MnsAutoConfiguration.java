package com.yexuejc.springboot.base.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.mns.client.MNSClient;

/**
 * 阿里云消息服务MNS相关配置
 *
 * @author maxf
 * @version 1.0
 * @ClassName MnsAutoConfiguration
 * @Description
 * @date 2018/11/1 10:04
 */
@Configuration
@ConditionalOnClass(MNSClient.class)
@EnableConfigurationProperties(MnsProperties.class)
public class MnsAutoConfiguration {
    private final MnsProperties properties;

    public MnsAutoConfiguration(MnsProperties properties) {
        super();
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MnsFacade mnsFacade() {
        return new MnsFacade(properties);
    }

}