package com.kedacom.redis.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedacom.redis.service.HomeService;

/**
 * 主页
 * @author lihongguang
 * @date 2018年8月8日
 */
@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;

	@RequestMapping("")
	public String home() {
		return "home";
	}

	@RequestMapping("/exists")
	@ResponseBody
	public Boolean exists(String key) {
		return homeService.exists(key);
	}

	@RequestMapping("/del")
	@ResponseBody
	public Boolean del(String key) {
		return homeService.del(key);
	}

	@RequestMapping("/expire")
	@ResponseBody
	public Boolean expire(String key, Long timeout) {
		return homeService.expire(key, timeout);
	}

	@RequestMapping("/ttl")
	@ResponseBody
	public Long ttl(String key) {
		return homeService.ttl(key);
	}

	@RequestMapping("/set")
	@ResponseBody
	public Boolean set(String key, String value) {
		homeService.set(key, value);
		return true;
	}

	@RequestMapping("/get")
	@ResponseBody
	public String get(String key) {
		return homeService.get(key);
	}

	@RequestMapping("/incr")
	@ResponseBody
	public Long incr(String key, Long delta) {
		return homeService.incrDecr(key, delta);
	}

	@RequestMapping("/decr")
	@ResponseBody
	public Long decr(String key, Long delta) {
		return homeService.incrDecr(key, -delta);
	}

	@RequestMapping("/rpush")
	@ResponseBody
	public Long rpush(String key, String list) {
		return homeService.rpush(key, list);
	}

	@RequestMapping("/lrange")
	@ResponseBody
	public List<Object> lrange(String key, Long start, Long end) {
		return homeService.lrange(key, start, end);
	}

	@RequestMapping("/lset")
	@ResponseBody
	public Boolean lset(String key, Long index, String value) {
		homeService.lset(key, index, value);
		return true;
	}

	@RequestMapping("/lrem")
	@ResponseBody
	public Long lrem(String key, Long count, String value) {
		return homeService.lrem(key, count, value);
	}

	@RequestMapping("/hmset")
	@ResponseBody
	public Boolean hmset(String key, String map) {
		homeService.hmset(key, map);
		return true;
	}

	@RequestMapping("/hgetall")
	@ResponseBody
	public Map<Object, Object> hgetall(String key) {
		return homeService.hgetall(key);
	}

	@RequestMapping("/hset")
	@ResponseBody
	public Boolean hset(String key, String hashKey, String value) {
		homeService.hset(key, hashKey, value);
		return true;
	}

	@RequestMapping("/hdel")
	@ResponseBody
	public Long hdel(String key, String list) {
		return homeService.hdel(key, list);
	}

	@RequestMapping("/sadd")
	@ResponseBody
	public Long sadd(String key, String list) {
		return homeService.sadd(key, list);
	}

	@RequestMapping("/smembers")
	@ResponseBody
	public Set<Object> smembers(String key) {
		return homeService.smembers(key);
	}

	@RequestMapping("/sinter")
	@ResponseBody
	public Set<Object> sinter(String list) {
		return homeService.sinter(list);
	}

	@RequestMapping("/sunion")
	@ResponseBody
	public Set<Object> sunion(String list) {
		return homeService.sunion(list);
	}

	@RequestMapping("/srem")
	@ResponseBody
	public Long srem(String key, String list) {
		return homeService.srem(key, list);
	}

	@RequestMapping("/zadd")
	@ResponseBody
	public Long zadd(String key, String set) {
		return homeService.zadd(key, set);
	}

	@RequestMapping("/zrange")
	@ResponseBody
	public Set<ZSetOperations.TypedTuple<Object>> zrange(String key, Long start, Long end) {
		return homeService.zrange(key, start, end);
	}
}
