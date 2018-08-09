package com.kedacom.redis.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月9日
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @see com.kedacom.redis.service.HomeService#exists(java.lang.String)
	 */

	@Override
	public Boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#del(java.lang.String)
	 */

	@Override
	public Boolean del(String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#expire(java.lang.String,
	 *      java.lang.Long)
	 */

	@Override
	public Boolean expire(String key, Long timeout) {
		return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#getExpire(java.lang.String)
	 */

	@Override
	public Long ttl(String key) {
		return redisTemplate.getExpire(key);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#set(java.lang.String,
	 *      java.lang.Object)
	 */

	@Override
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#get(java.lang.String)
	 */

	@Override
	public String get(String key) {
		Object result = redisTemplate.opsForValue().get(key);
		if (null == result) {
			return "null";
		} else {
			return (String) result;
		}
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#incr(java.lang.String)
	 */

	@Override
	public Long incrDecr(String key, Long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#rpush(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public Long rpush(String key, String list) {
		String[] stringArray = list.split(" ");
		return redisTemplate.opsForList().rightPushAll(key, (Object[]) stringArray);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#lrange(java.lang.String,
	 *      java.lang.Long, java.lang.Long)
	 */

	@Override
	public List<Object> lrange(String key, Long start, Long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#lset(java.lang.String,
	 *      java.lang.Long, java.lang.String)
	 */

	@Override
	public void lset(String key, Long index, String value) {
		redisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#lrem(java.lang.String,
	 *      java.lang.Long, java.lang.String)
	 */

	@Override
	public Long lrem(String key, Long count, String value) {
		return redisTemplate.opsForList().remove(key, count, value);
	}

}
