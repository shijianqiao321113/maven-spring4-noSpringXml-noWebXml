package com.spring4.pure.annotation.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring4.pure.annotation.redis.RedisOper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisOperImpl implements RedisOper{
	
	@Autowired
	private JedisPool jedisPool;

	@Override
	public Boolean setUserInfo_Hs(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		try{
			long result = jedis.hset(key, field, value);
			if(result<=0){
				return Boolean.FALSE;
			}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return Boolean.TRUE;
	}
	
}
