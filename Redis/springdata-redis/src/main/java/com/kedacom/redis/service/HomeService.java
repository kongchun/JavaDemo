package com.kedacom.redis.service;

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
}
