package com.kedacom.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Spring配置
 * @author lihongguang
 * @date 2018年8月8日
 */
@Configuration
@ComponentScan("com.kedacom.redis")
public class RootConfig {

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数 默认8
		jedisPoolConfig.setMaxIdle(10);
		// 连接池的最大数据库连接数 默认8
		jedisPoolConfig.setMaxTotal(10);
		// 最大建立连接等待时间 默认-1(无限制)
		jedisPoolConfig.setMaxWaitMillis(1000);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
		// 每次逐出检查时 逐出的最大数目 默认3
		jedisPoolConfig.setNumTestsPerEvictionRun(1024);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程 默认-1
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
		return jedisPoolConfig;
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {

		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisClientConfiguration = (JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		jedisClientConfiguration.poolConfig(jedisPoolConfig);

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		// 实际上是默认
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);

		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);

		// 默认UTF-8
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// 默认Spring对象序列化
		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
		redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

		return redisTemplate;
	}
}
