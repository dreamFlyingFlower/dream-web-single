package com.dream.web.cache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.system.cache.RedisCache;
import com.dream.web.entity.ParamsEntity;
import com.wy.collection.ListTool;

/**
 * 参数管理 Cache
 *
 * @author 飞花梦影
 * @date 2023-08-08 10:56:42
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class ParamsCache {

	@Autowired
	private RedisCache redisCache;

	/**
	 * 参数管理 KEY
	 */
	private final String SYSTEM_PARAMS_KEY = "system:params";

	/**
	 * 保存参数列表到缓存
	 *
	 * @param list 参数列表
	 */
	public void saveList(List<ParamsEntity> list) {
		if (ListTool.isEmpty(list)) {
			return;
		}

		// list 转成 map
		Map<String, Object> map =
				list.stream().collect(Collectors.toMap(ParamsEntity::getParamKey, ParamsEntity::getParamValue));

		redisCache.hMSet(SYSTEM_PARAMS_KEY, map, RedisCache.NOT_EXPIRE);
	}

	/**
	 * 删除缓存中的全部参数列表
	 */
	public void delList() {
		redisCache.delete(SYSTEM_PARAMS_KEY);
	}

	/**
	 * 根据参数键，获取参数值
	 *
	 * @param paramKey 参数键
	 */
	public String get(String paramKey) {
		return (String) redisCache.hGet(SYSTEM_PARAMS_KEY, paramKey);
	}

	/**
	 * 根据参数键，获取参数值
	 *
	 * @param paramKey 参数键
	 * @param paramValue 参数值
	 */
	public void save(String paramKey, String paramValue) {
		redisCache.hSet(SYSTEM_PARAMS_KEY, paramKey, paramValue);
	}

	/**
	 * 根据参数键，删除参数值
	 *
	 * @param paramKey 参数键
	 */
	public void del(Object... paramKey) {
		redisCache.hDel(SYSTEM_PARAMS_KEY, paramKey);
	}
}