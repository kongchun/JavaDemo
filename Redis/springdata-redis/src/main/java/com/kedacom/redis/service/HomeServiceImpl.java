package com.kedacom.redis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
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
		String[] values = list.split(" ");
		return redisTemplate.opsForList().rightPushAll(key, (Object[]) values);
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

	/**
	 * @see com.kedacom.redis.service.HomeService#hmset(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public void hmset(String key, String map) {
		String[] stringArray = map.split(" ");
		Map<String, String> m = new HashMap<String, String>();
		String mapKey = "";
		String mapValue = "";
		for (int i = 0; i < stringArray.length; i++) {
			if (0 == i % 2) {
				mapKey = stringArray[i];
			} else {
				mapValue = stringArray[i];
				m.put(mapKey, mapValue);
			}
		}
		redisTemplate.opsForHash().putAll(key, m);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#hgetall(java.lang.String)
	 */

	@Override
	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#hset(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */

	@Override
	public void hset(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#hdel(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public Long hdel(String key, String list) {
		String[] hashKeys = list.split(" ");
		return redisTemplate.opsForHash().delete(key, (Object[]) hashKeys);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#sadd(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public Long sadd(String key, String list) {
		String[] values = list.split(" ");
		return redisTemplate.opsForSet().add(key, (Object[]) values);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#smembers(java.lang.String)
	 */

	@Override
	public Set<Object> smembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#sinter(java.lang.String)
	 */

	@Override
	public Set<Object> sinter(String list) {
		String[] values = list.split(" ");
		String key = values[0];
		ArrayList<String> otherKeys = new ArrayList<String>(Arrays.asList(values));
		otherKeys.remove(0);
		return redisTemplate.opsForSet().intersect(key, otherKeys);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#sunion(java.lang.String)
	 */

	@Override
	public Set<Object> sunion(String list) {
		String[] values = list.split(" ");
		String key = values[0];
		ArrayList<String> otherKeys = new ArrayList<String>(Arrays.asList(values));
		otherKeys.remove(0);
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#srem(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public Long srem(String key, String list) {
		String[] values = list.split(" ");
		return redisTemplate.opsForSet().remove(key, (Object[]) values);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#zadd(java.lang.String,
	 *      java.lang.String, java.lang.Long)
	 */

	@Override
	public Long zadd(String key, String set) {
		Set<TypedTuple<Object>> tuples = new HashSet<TypedTuple<Object>>();
		String[] stringArray = set.split(" ");
		Double score = 0.0;
		String value = "";
		for (int i = 0; i < stringArray.length; i++) {
			if (0 == i % 2) {
				score = Double.parseDouble(stringArray[i]);
			} else {
				value = stringArray[i];
				tuples.add(new DefaultTypedTuple<Object>(value, score));
			}
		}
		return redisTemplate.opsForZSet().add(key, tuples);
	}

	/**
	 * @see com.kedacom.redis.service.HomeService#zrange(java.lang.String,
	 *      java.lang.Long, java.lang.Long)
	 */

	@Override
	public Set<ZSetOperations.TypedTuple<Object>> zrange(String key, Long start, Long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}
}
