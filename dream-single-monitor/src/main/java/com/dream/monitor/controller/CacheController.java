package com.dream.monitor.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.monitor.vo.Cache;
import com.wy.lang.StrHelper;
import com.wy.result.Result;

/**
 * 缓存监控
 *
 * @author 飞花梦影
 * @date 2023-07-10 17:53:59
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("monitor/cache")
public class CacheController {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * Redis详情
	 */
	@GetMapping("info")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<Map<String, Object>> getInfo() {
		Map<String, Object> result = new HashMap<>();
		// Step 1: 获取Redis详情
		Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
		result.put("info", info);
		// Step 2: 获取Key的数量
		Object dbSize = redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::dbSize);
		result.put("keyCount", dbSize);
		// Step 3: 获取请求次数
		List<Map<String, Object>> pieList = new ArrayList<>();
		Properties commandStats = (Properties) redisTemplate
				.execute((RedisCallback<Object>) connection -> connection.info("commandStats"));
		if (commandStats != null && commandStats.size() != 0) {
			commandStats.stringPropertyNames().forEach(key -> {
				Map<String, Object> data = new HashMap<>();
				String property = commandStats.getProperty(key);
				data.put("name", key.substring(8));
				data.put("value", StrHelper.subBetween(property, "calls=", ",use"));
				pieList.add(data);
			});
		}
		result.put("commandStats", pieList);
		return Result.ok(result);
	}

	/**
	 * 获取所有的Key
	 */
	@GetMapping("getCacheKeys")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<Set<String>> getCacheKeys() {
		Set<String> cacheKeys = redisTemplate.keys("*");
		return Result.ok(cacheKeys);
	}

	/**
	 * 获取结构化键下的Key值
	 *
	 * @param cacheKey
	 */
	@GetMapping("getCacheKeys/{cacheKey}")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<Set<String>> getCacheKeys(@PathVariable String cacheKey) {
		Set<String> cacheKeys = redisTemplate.keys(cacheKey + "*");
		return Result.ok(cacheKeys);
	}

	/**
	 * 获取指定键的值
	 *
	 * @param cacheKey
	 */
	@GetMapping("getCacheValue/{cacheKey}")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<Cache> getCacheValue(@PathVariable String cacheKey) {
		Object cacheValue = redisTemplate.opsForValue().get(cacheKey);
		Cache cache = new Cache(cacheKey, cacheValue);
		return Result.ok(cache);
	}

	/**
	 * 删除指定键的缓存
	 *
	 * @param cacheKey > Key值
	 */
	@DeleteMapping("delCacheKey/{cacheKey}")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<String> delCacheKey(@PathVariable String cacheKey) {
		boolean flag = redisTemplate.delete(cacheKey);
		if (flag) {
			return Result.ok();
		} else {
			return Result.error(200, "处理失败!");
		}
	}

	/**
	 * 删除结构化键下的缓存
	 *
	 * @param cacheKey > Key值
	 */
	@DeleteMapping("delCacheKeys/{cacheKey}")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<String> delCacheKeys(@PathVariable String cacheKey) {
		Collection<String> cacheKeys = redisTemplate.keys(cacheKey + "*");
		redisTemplate.delete(cacheKeys);
		return Result.ok();
	}

	/**
	 * 删除全部缓存
	 */
	@DeleteMapping("delCacheAll")
	@PreAuthorize("hasAuthority('monitor:cache:all')")
	public Result<String> delCacheAll() {
		Collection<String> cacheKeys = redisTemplate.keys("*");
		redisTemplate.delete(cacheKeys);
		return Result.ok();
	}

}