package com.yexuejc.springboot.base.autoconfigure;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Sentinel;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass({ JedisConnection.class, RedisOperations.class, Jedis.class })
@EnableConfigurationProperties(RedisProperties.class)
@Order(1)
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

	/**
	 * Redis connection configuration.
	 */
	@Configuration
	@ConditionalOnClass(GenericObjectPool.class)
	protected static class RedisConnectionConfiguration {

		private final RedisProperties properties;

		private final RedisSentinelConfiguration sentinelConfiguration;

		private final RedisClusterConfiguration clusterConfiguration;

		public RedisConnectionConfiguration(RedisProperties properties,
				ObjectProvider<RedisSentinelConfiguration> sentinelConfiguration,
				ObjectProvider<RedisClusterConfiguration> clusterConfiguration) {
			this.properties = properties;
			this.sentinelConfiguration = sentinelConfiguration.getIfAvailable();
			this.clusterConfiguration = clusterConfiguration.getIfAvailable();
		}

		@Primary
		@Bean(BEAN_REDIS_FACTORY0)
		@ConditionalOnProperty(name = "yexuejc.redis.db0", matchIfMissing = true)
		public JedisConnectionFactory redisConnectionFactory0() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 0);
		}

		@Bean(BEAN_REDIS_FACTORY1)
		@ConditionalOnProperty(name = "yexuejc.redis.db1")
		public JedisConnectionFactory redisConnectionFactory1() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 1);
		}

		@Bean(BEAN_REDIS_FACTORY2)
		@ConditionalOnProperty(name = "yexuejc.redis.db2")
		public JedisConnectionFactory redisConnectionFactory2() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 2);
		}

		@Bean(BEAN_REDIS_FACTORY3)
		@ConditionalOnProperty(name = "yexuejc.redis.db3")
		public JedisConnectionFactory redisConnectionFactory3() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 3);
		}

		@Bean(BEAN_REDIS_FACTORY4)
		@ConditionalOnProperty(name = "yexuejc.redis.db4")
		public JedisConnectionFactory redisConnectionFactory4() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 4);
		}

		@Bean(BEAN_REDIS_FACTORY5)
		@ConditionalOnProperty(name = "yexuejc.redis.db5")
		public JedisConnectionFactory redisConnectionFactory5() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 5);
		}

		@Bean(BEAN_REDIS_FACTORY6)
		@ConditionalOnProperty(name = "yexuejc.redis.db6")
		public JedisConnectionFactory redisConnectionFactory6() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 6);
		}

		@Bean(BEAN_REDIS_FACTORY7)
		@ConditionalOnProperty(name = "yexuejc.redis.db7")
		public JedisConnectionFactory redisConnectionFactory7() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 7);
		}

		@Bean(BEAN_REDIS_FACTORY8)
		@ConditionalOnProperty(name = "yexuejc.redis.db8")
		public JedisConnectionFactory redisConnectionFactory8() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 8);
		}

		@Bean(BEAN_REDIS_FACTORY9)
		@ConditionalOnProperty(name = "yexuejc.redis.db9")
		public JedisConnectionFactory redisConnectionFactory9() throws UnknownHostException {
			return applyProperties(createJedisConnectionFactory(), 9);
		}

		protected final JedisConnectionFactory applyProperties(JedisConnectionFactory factory, int database) {
			configureConnection(factory);
			if (this.properties.isSsl()) {
				factory.setUseSsl(true);
			}
			factory.setDatabase(database);
			if (this.properties.getTimeout() > 0) {
				factory.setTimeout(this.properties.getTimeout());
			}
			return factory;
		}

		private void configureConnection(JedisConnectionFactory factory) {
			if (StringUtils.hasText(this.properties.getUrl())) {
				configureConnectionFromUrl(factory);
			} else {
				factory.setHostName(this.properties.getHost());
				factory.setPort(this.properties.getPort());
				if (this.properties.getPassword() != null) {
					factory.setPassword(this.properties.getPassword());
				}
			}
		}

		private void configureConnectionFromUrl(JedisConnectionFactory factory) {
			String url = this.properties.getUrl();
			if (url.startsWith("rediss://")) {
				factory.setUseSsl(true);
			}
			try {
				URI uri = new URI(url);
				factory.setHostName(uri.getHost());
				factory.setPort(uri.getPort());
				if (uri.getUserInfo() != null) {
					String password = uri.getUserInfo();
					int index = password.lastIndexOf(":");
					if (index >= 0) {
						password = password.substring(index + 1);
					}
					factory.setPassword(password);
				}
			} catch (URISyntaxException ex) {
				throw new IllegalArgumentException("Malformed 'spring.redis.url' " + url, ex);
			}
		}

		protected final RedisSentinelConfiguration getSentinelConfig() {
			if (this.sentinelConfiguration != null) {
				return this.sentinelConfiguration;
			}
			Sentinel sentinelProperties = this.properties.getSentinel();
			if (sentinelProperties != null) {
				RedisSentinelConfiguration config = new RedisSentinelConfiguration();
				config.master(sentinelProperties.getMaster());
				config.setSentinels(createSentinels(sentinelProperties));
				return config;
			}
			return null;
		}

		/**
		 * Create a {@link RedisClusterConfiguration} if necessary.
		 * 
		 * @return {@literal null} if no cluster settings are set.
		 */
		protected final RedisClusterConfiguration getClusterConfiguration() {
			if (this.clusterConfiguration != null) {
				return this.clusterConfiguration;
			}
			if (this.properties.getCluster() == null) {
				return null;
			}
			Cluster clusterProperties = this.properties.getCluster();
			RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());

			if (clusterProperties.getMaxRedirects() != null) {
				config.setMaxRedirects(clusterProperties.getMaxRedirects());
			}
			return config;
		}

		private List<RedisNode> createSentinels(Sentinel sentinel) {
			List<RedisNode> nodes = new ArrayList<RedisNode>();
			for (String node : StringUtils.commaDelimitedListToStringArray(sentinel.getNodes())) {
				try {
					String[] parts = StringUtils.split(node, ":");
					Assert.state(parts.length == 2, "Must be defined as 'host:port'");
					nodes.add(new RedisNode(parts[0], Integer.valueOf(parts[1])));
				} catch (RuntimeException ex) {
					throw new IllegalStateException("Invalid redis sentinel " + "property '" + node + "'", ex);
				}
			}
			return nodes;
		}

		private JedisConnectionFactory createJedisConnectionFactory() {
			JedisPoolConfig poolConfig = this.properties.getPool() != null ? jedisPoolConfig() : new JedisPoolConfig();

			if (getSentinelConfig() != null) {
				return new JedisConnectionFactory(getSentinelConfig(), poolConfig);
			}
			if (getClusterConfiguration() != null) {
				return new JedisConnectionFactory(getClusterConfiguration(), poolConfig);
			}
			return new JedisConnectionFactory(poolConfig);
		}

		private JedisPoolConfig jedisPoolConfig() {
			JedisPoolConfig config = new JedisPoolConfig();
			RedisProperties.Pool props = this.properties.getPool();
			config.setMaxTotal(props.getMaxActive());
			config.setMaxIdle(props.getMaxIdle());
			config.setMinIdle(props.getMinIdle());
			config.setMaxWaitMillis(props.getMaxWait());
			return config;
		}

	}

	/**
	 * Standard Redis configuration.
	 */
	@Configuration
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
