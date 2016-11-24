package com.spring4.pure.annotation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private Integer port;
	
	@Value("${redis.maxActive}")
	private Integer maxActive;
	
	@Value("${redis.maxIdle}")
	private Integer maxIdle;
	
	@Value("${redis.minIdle}")
	private Integer minIdle;
	
	@Value("${redis.maxWait}")
	private Integer maxWait;
	
	@Value("${redis.testOnBorrow}")
	private Boolean testOnBorrow;
	
	@Value("${redis.testOnReturn}")
	private Boolean testOnReturn;
	
	@Value("${redis.testWhileIdle}")
	private Boolean testWhileIdle;
	
	@Value("${redis.whenExhaustedAction}")
	private Byte whenExhaustedAction;
	
	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxActive(this.maxActive);
		poolConfig.setMaxIdle(this.maxIdle);
		poolConfig.setMinIdle(this.minIdle);
		poolConfig.setMaxWait(this.maxWait);
		poolConfig.setTestOnBorrow(this.testOnBorrow);
		poolConfig.setTestOnReturn(this.testOnReturn);
		poolConfig.setTestWhileIdle(this.testWhileIdle);
		poolConfig.setWhenExhaustedAction(this.whenExhaustedAction);
		return poolConfig;
	}
	
	@Bean(name = "jedisPool")
	public JedisPool getJedisPool(){
		return new JedisPool(getJedisPoolConfig(),this.host,this.port);
	}
}
