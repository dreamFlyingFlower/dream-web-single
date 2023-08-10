package com.dream.message.cache;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.framework.cache.RedisCache;
import com.dream.message.sms.config.SmsConfig;

import lombok.AllArgsConstructor;

/**
 * 短信平台 Cache
 *
 * @author 飞花梦影
 * @date 2023-07-12 09:51:45
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class SmsPlatformCache {

	private final RedisCache redisCache;

	/**
	 * 短信平台轮询KEY
	 */
	private final String SMS_ROUND_KEY = "message:sms:round";

	/**
	 * 短信平台列表KEY
	 */
	private final String SMS_PLATFORM_KEY = "message:sms:platform";

	/**
	 * 获取短信轮询值
	 */
	public Long getRoundValue() {
		return redisCache.increment(SMS_ROUND_KEY);
	}

	@SuppressWarnings("unchecked")
	public List<SmsConfig> list() {
		return (List<SmsConfig>) redisCache.get(SMS_PLATFORM_KEY);
	}

	public void save(List<SmsConfig> list) {
		redisCache.set(SMS_PLATFORM_KEY, list);
	}

	public void delete() {
		redisCache.delete(SMS_PLATFORM_KEY);
	}
}