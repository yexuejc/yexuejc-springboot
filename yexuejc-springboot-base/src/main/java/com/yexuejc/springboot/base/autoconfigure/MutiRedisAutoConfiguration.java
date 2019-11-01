package com.yexuejc.springboot.base.autoconfigure;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import java.net.UnknownHostException;

/**
 * 多个database配置
 *
 * @author maxf
 * @PackageName com.yexuejc.springboot.base.autoconfigure
 * @Description
 * @date 2018/9/26 15:27
 */
@Configuration
@ConditionalOnClass({JedisConnection.class, RedisOperations.class, Jedis.class})
@EnableConfigurationProperties(RedisProperties.class)
@Order(1)
@ConditionalOnProperty(name = "yexuejc.autoconfigure.redis.enable", matchIfMissing = false)
public class MutiRedisAutoConfiguration {
    public static final String BEAN_REDIS_FACTORY0 = "redisConnectionFactory";
    public static final String BEAN_REDIS_TEMPLATE0 = "redisTemplate";
    public static final String BEAN_REDIS_STRING_TEMPLATE0 = "stringRedisTemplate";

    public static final String BEAN_REDIS_FACTORY1 = "redis-factory-1";
    public static final String BEAN_REDIS_TEMPLATE1 = "redis-template-1";
    public static final String BEAN_REDIS_STRING_TEMPLATE1 = "redis-string-template-1";
    public static final String BEAN_REDIS_FACTORY2 = "redis-factory-2";
    public static final String BEAN_REDIS_TEMPLATE2 = "redis-template-2";
    public static final String BEAN_REDIS_STRING_TEMPLATE2 = "redis-string-template-2";
    public static final String BEAN_REDIS_FACTORY3 = "redis-factory-3";
    public static final String BEAN_REDIS_TEMPLATE3 = "redis-template-3";
    public static final String BEAN_REDIS_STRING_TEMPLATE3 = "redis-string-template-3";
    public static final String BEAN_REDIS_FACTORY4 = "redis-factory-4";
    public static final String BEAN_REDIS_TEMPLATE4 = "redis-template-4";
    public static final String BEAN_REDIS_STRING_TEMPLATE4 = "redis-string-template-4";
    public static final String BEAN_REDIS_FACTORY5 = "redis-factory-5";
    public static final String BEAN_REDIS_TEMPLATE5 = "redis-template-5";
    public static final String BEAN_REDIS_STRING_TEMPLATE5 = "redis-string-template-5";
    public static final String BEAN_REDIS_FACTORY6 = "redis-factory-6";
    public static final String BEAN_REDIS_TEMPLATE6 = "redis-template-6";
    public static final String BEAN_REDIS_STRING_TEMPLATE6 = "redis-string-template-6";
    public static final String BEAN_REDIS_FACTORY7 = "redis-factory-7";
    public static final String BEAN_REDIS_TEMPLATE7 = "redis-template-7";
    public static final String BEAN_REDIS_STRING_TEMPLATE7 = "redis-string-template-7";
    public static final String BEAN_REDIS_FACTORY8 = "redis-factory-8";
    public static final String BEAN_REDIS_TEMPLATE8 = "redis-template-8";
    public static final String BEAN_REDIS_STRING_TEMPLATE8 = "redis-string-template-8";
    public static final String BEAN_REDIS_FACTORY9 = "redis-factory-9";
    public static final String BEAN_REDIS_TEMPLATE9 = "redis-template-9";
    public static final String BEAN_REDIS_STRING_TEMPLATE9 = "redis-string-template-9";

    @Configuration
    @ConditionalOnClass(GenericObjectPool.class)
    @ConditionalOnProperty(name = "yexuejc.autoconfigure.redis.enable", matchIfMissing = false)
    protected static class RedisConnectionConfiguration {

        private final RedisProperties properties;

        public RedisConnectionConfiguration(RedisProperties properties) {
            this.properties = properties;
        }

        @Primary
        @Bean(BEAN_REDIS_FACTORY0)
        @ConditionalOnProperty(name = "yexuejc.redis.db0", matchIfMissing = true)
        public JedisConnectionFactory redisConnectionFactory0() throws UnknownHostException {
            return createJedisConnectionFactory(0);
        }

        @Bean(BEAN_REDIS_FACTORY1)
        @ConditionalOnProperty(name = "yexuejc.redis.db1")
        public JedisConnectionFactory redisConnectionFactory1() throws UnknownHostException {
            return createJedisConnectionFactory(1);
        }

        @Bean(BEAN_REDIS_FACTORY2)
        @ConditionalOnProperty(name = "yexuejc.redis.db2")
        public JedisConnectionFactory redisConnectionFactory2() throws UnknownHostException {
            return createJedisConnectionFactory(2);
        }

        @Bean(BEAN_REDIS_FACTORY3)
        @ConditionalOnProperty(name = "yexuejc.redis.db3")
        public JedisConnectionFactory redisConnectionFactory3() throws UnknownHostException {
            return createJedisConnectionFactory(3);
        }

        @Bean(BEAN_REDIS_FACTORY4)
        @ConditionalOnProperty(name = "yexuejc.redis.db4")
        public JedisConnectionFactory redisConnectionFactory4() throws UnknownHostException {
            return createJedisConnectionFactory(4);
        }

        @Bean(BEAN_REDIS_FACTORY5)
        @ConditionalOnProperty(name = "yexuejc.redis.db5")
        public JedisConnectionFactory redisConnectionFactory5() throws UnknownHostException {
            return createJedisConnectionFactory(5);
        }

        @Bean(BEAN_REDIS_FACTORY6)
        @ConditionalOnProperty(name = "yexuejc.redis.db6")
        public JedisConnectionFactory redisConnectionFactory6() throws UnknownHostException {
            return createJedisConnectionFactory(6);
        }

        @Bean(BEAN_REDIS_FACTORY7)
        @ConditionalOnProperty(name = "yexuejc.redis.db7")
        public JedisConnectionFactory redisConnectionFactory7() throws UnknownHostException {
            return createJedisConnectionFactory(7);
        }

        @Bean(BEAN_REDIS_FACTORY8)
        @ConditionalOnProperty(name = "yexuejc.redis.db8")
        public JedisConnectionFactory redisConnectionFactory8() throws UnknownHostException {
            return createJedisConnectionFactory(8);
        }

        @Bean(BEAN_REDIS_FACTORY9)
        @ConditionalOnProperty(name = "yexuejc.redis.db9")
        public JedisConnectionFactory redisConnectionFactory9() throws UnknownHostException {
            return createJedisConnectionFactory(9);
        }

        private JedisConnectionFactory createJedisConnectionFactory(int database) {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(properties.getHost());
            redisStandaloneConfiguration.setPort(properties.getPort());
            redisStandaloneConfiguration.setDatabase(database);
            redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPassword()));

            JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();

            JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                    jedisClientConfiguration.build());
            return factory;
        }

    }


    /**
     * Standard Redis configuration.
     */
    @Configuration
    @ConditionalOnProperty(name = "yexuejc.autoconfigure.redis.enable", matchIfMissing = false)
    protected static class RedisConfiguration {

        @Primary
        @Bean(BEAN_REDIS_TEMPLATE0)
        @ConditionalOnProperty(name = "yexuejc.redis.db0", matchIfMissing = true)
        public RedisTemplate<Object, Object> redisTemplate(
                @Qualifier(BEAN_REDIS_FACTORY0) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE0)
        @ConditionalOnProperty(name = "yexuejc.redis.db0", matchIfMissing = true)
        public StringRedisTemplate stringRedisTemplate(
                @Qualifier(BEAN_REDIS_FACTORY0) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE1)
        @ConditionalOnProperty(name = "yexuejc.redis.db1")
        public RedisTemplate<Object, Object> redisTemplate1(
                @Qualifier(BEAN_REDIS_FACTORY1) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE1)
        @ConditionalOnProperty(name = "yexuejc.redis.db1")
        public StringRedisTemplate stringRedisTemplate1(
                @Qualifier(BEAN_REDIS_FACTORY1) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE2)
        @ConditionalOnProperty(name = "yexuejc.redis.db2")
        public RedisTemplate<Object, Object> redisTemplate2(
                @Qualifier(BEAN_REDIS_FACTORY2) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE2)
        @ConditionalOnProperty(name = "yexuejc.redis.db2")
        public StringRedisTemplate stringRedisTemplate2(
                @Qualifier(BEAN_REDIS_FACTORY2) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE3)
        @ConditionalOnProperty(name = "yexuejc.redis.db3")
        public RedisTemplate<Object, Object> redisTemplate3(
                @Qualifier(BEAN_REDIS_FACTORY3) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE3)
        @ConditionalOnProperty(name = "yexuejc.redis.db3")
        public StringRedisTemplate stringRedisTemplate3(
                @Qualifier(BEAN_REDIS_FACTORY3) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE4)
        @ConditionalOnProperty(name = "yexuejc.redis.db4")
        public RedisTemplate<Object, Object> redisTemplate4(
                @Qualifier(BEAN_REDIS_FACTORY4) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE4)
        @ConditionalOnProperty(name = "yexuejc.redis.db4")
        public StringRedisTemplate stringRedisTemplate4(
                @Qualifier(BEAN_REDIS_FACTORY4) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE5)
        @ConditionalOnProperty(name = "yexuejc.redis.db5")
        public RedisTemplate<Object, Object> redisTemplate5(
                @Qualifier(BEAN_REDIS_FACTORY5) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE5)
        @ConditionalOnProperty(name = "yexuejc.redis.db5")
        public StringRedisTemplate stringRedisTemplate5(
                @Qualifier(BEAN_REDIS_FACTORY5) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE6)
        @ConditionalOnProperty(name = "yexuejc.redis.db6")
        public RedisTemplate<Object, Object> redisTemplate6(
                @Qualifier(BEAN_REDIS_FACTORY6) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE6)
        @ConditionalOnProperty(name = "yexuejc.redis.db6")
        public StringRedisTemplate stringRedisTemplate6(
                @Qualifier(BEAN_REDIS_FACTORY6) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE7)
        @ConditionalOnProperty(name = "yexuejc.redis.db7")
        public RedisTemplate<Object, Object> redisTemplate7(
                @Qualifier(BEAN_REDIS_FACTORY7) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE7)
        @ConditionalOnProperty(name = "yexuejc.redis.db7")
        public StringRedisTemplate stringRedisTemplate7(
                @Qualifier(BEAN_REDIS_FACTORY7) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE8)
        @ConditionalOnProperty(name = "yexuejc.redis.db8")
        public RedisTemplate<Object, Object> redisTemplate8(
                @Qualifier(BEAN_REDIS_FACTORY8) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE8)
        @ConditionalOnProperty(name = "yexuejc.redis.db8")
        public StringRedisTemplate stringRedisTemplate8(
                @Qualifier(BEAN_REDIS_FACTORY8) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_TEMPLATE9)
        @ConditionalOnProperty(name = "yexuejc.redis.db9")
        public RedisTemplate<Object, Object> redisTemplate9(
                @Qualifier(BEAN_REDIS_FACTORY9) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createRedisTemplate(redisConnectionFactory);
        }

        @Bean(BEAN_REDIS_STRING_TEMPLATE9)
        @ConditionalOnProperty(name = "yexuejc.redis.db9")
        public StringRedisTemplate stringRedisTemplate9(
                @Qualifier(BEAN_REDIS_FACTORY9) RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            return createStringRedisTemplate(redisConnectionFactory);
        }

        private RedisTemplate<Object, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
            template.setKeySerializer(new StringRedisSerializer());
            template.setDefaultSerializer(new StringRedisSerializer());
            template.setValueSerializer(new StringRedisSerializer());
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        private StringRedisTemplate createStringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

    }
}
