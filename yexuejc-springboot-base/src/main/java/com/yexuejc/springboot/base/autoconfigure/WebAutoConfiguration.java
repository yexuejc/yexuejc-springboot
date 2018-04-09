package com.yexuejc.springboot.base.autoconfigure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yexuejc.base.http.Resps;
import com.yexuejc.base.util.DateUtil;
import com.yexuejc.springboot.base.filter.ValidationFilter;
import com.yexuejc.springboot.base.filter.ValidationFilterProperties;
import com.yexuejc.springboot.base.interceptor.LogInterceptor;
import com.yexuejc.springboot.base.util.LogUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvc相关配置
 *
 * @PackageName: com.yexuejc.util.base.http
 * @Description:
 * @author: maxf
 * @date: 2018/1/11 19:51
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ValidationFilterProperties.class)
public class WebAutoConfiguration extends WebMvcConfigurerAdapter {


    public WebAutoConfiguration(ValidationFilterProperties properties) {
        this.properties = properties;
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 日志拦截器
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 重写jsonConverter参数
     *
     * @param objectMapper
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(DateUtil.DATE_TIME_FORMAT);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    private final ValidationFilterProperties properties;

    /**
     * 添加校验过滤器，目前校验HTTP Header是否符合规范
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "yexuejc.web.validation-filter.enable", matchIfMissing = true)
    public FilterRegistrationBean validationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ValidationFilter(properties));
        registration.addUrlPatterns("/*");
        registration.setName("validationFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 20);
        return registration;
    }

    /**
     * 全局异常处理
     */
    @ControllerAdvice
    static class GlobalExceptionHandler {
        private static final String ERROR_MSG = "系统错误，请联系管理员";

        /**
         * 出现异常时用于返回Json数据
         *
         * @param e
         * @return
         */
        @ExceptionHandler(value = Throwable.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Resps<Object> jsonErrorHandler(Throwable e) {
            LogUtil.exceptionLogger.error(e.getMessage(), e);
            return Resps.error(ERROR_MSG);
        }
    }
}
