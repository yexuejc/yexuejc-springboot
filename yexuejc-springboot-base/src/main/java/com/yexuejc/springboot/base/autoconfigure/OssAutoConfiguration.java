package com.yexuejc.springboot.base.autoconfigure;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS相关配置
 */
@Configuration
@ConditionalOnClass({ OSSClient.class })
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    private final OssProperties properties;

    public OssAutoConfiguration(OssProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public OssFacade ossFacade() {
        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setSupportCname(false);
        return new OssFacade(properties, configuration);
    }
}