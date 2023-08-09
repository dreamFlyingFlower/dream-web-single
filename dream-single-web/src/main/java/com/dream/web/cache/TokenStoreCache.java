package com.dream.web.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dream.system.cache.RedisCache;
import com.dream.system.cache.RedisKeys;
import com.dream.web.security.SecurityUserDetails;

/**
 * 认证 Cache
 *
 * @author 飞花梦影
 * @date 2023-07-08 17:05:36
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Component
public class TokenStoreCache {

	@Autowired
	private RedisCache redisCache;

	public void saveUser(String accessToken, SecurityUserDetails user) {
		String key = RedisKeys.getAccessTokenKey(accessToken);
		redisCache.set(key, user);
	}

	public SecurityUserDetails getUser(String accessToken) {
		String key = RedisKeys.getAccessTokenKey(accessToken);
		return (SecurityUserDetails) redisCache.get(key);
	}

	public void deleteUser(String accessToken) {
		String key = RedisKeys.getAccessTokenKey(accessToken);
		redisCache.delete(key);
	}
}