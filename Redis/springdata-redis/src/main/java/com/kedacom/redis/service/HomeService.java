package com.kedacom.redis.service;

import java.util.List;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月9日
 */

public interface HomeService {

	Boolean exists(String key);

	Boolean del(String key);

	Boolean expire(String key, Long timeout);

	Long ttl(String key);

	void set(String key, String value);

	String get(String key);

	Long incrDecr(String key, Long delta);

	Long rpush(String key, String list);

	List<Object> lrange(String key, Long start, Long end);

	void lset(String key, Long index, String value);

	Long lrem(String key, Long count, String value);
}
