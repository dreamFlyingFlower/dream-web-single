package com.dream.message.cache;

import org.springframework.stereotype.Service;

import com.dream.system.cache.RedisCache;

import lombok.AllArgsConstructor;

/**
 * 短信发送 Cache
 *
 * @author 飞花梦影
 * @date 2023-07-12 09:51:56
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class SmsSendCache {

	private final RedisCache redisCache;

	/**
	 * 获取发送手机短信验证码KEY
	 *
	 * @param mobile 手机号
	 * @return KEY
	 */
	private String getCodeKey(String mobile) {
		return "message:sms:code" + mobile;
	}

	public void saveCode(String mobile, String code) {
		String key = getCodeKey(mobile);

		// 保存到Redis，有效期10分钟
		redisCache.set(key, code, 10 * 60);
	}

	public String getCode(String mobile) {
		String key = getCodeKey(mobile);
		return (String) redisCache.get(key);
	}

	public void deleteCode(String mobile) {
		String key = getCodeKey(mobile);
		redisCache.delete(key);
	}
}