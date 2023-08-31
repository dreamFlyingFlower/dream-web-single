package com.dream.system.service;

import com.dream.system.entity.ParamsEntity;
import com.dream.system.query.ParamsQuery;
import com.dream.system.vo.ParamsVO;

import dream.framework.core.json.JsonHelpers;
import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 参数管理
 * 
 * @author 飞花梦影
 * @date 2023-08-21 17:00:42
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface ParamsService extends BaseServices<ParamsEntity, ParamsVO, ParamsQuery> {

	/**
	 * 根据paramKey,获取字符串值
	 *
	 * @param paramKey 参数Key
	 */
	String getString(String paramKey);

	/**
	 * 根据paramKey,获取整型值
	 *
	 * @param paramKey 参数Key
	 */
	default int getInt(String paramKey) {
		return Integer.parseInt(getString(paramKey));
	}

	/**
	 * 根据paramKey,获取布尔值
	 *
	 * @param paramKey 参数Key
	 */
	default boolean getBoolean(String paramKey) {
		return Boolean.parseBoolean(getString(paramKey));
	}

	/**
	 * 根据paramKey,获取对象值
	 *
	 * @param paramKey 参数Key
	 * @param valueType 类型
	 */
	default <T> T getJSONObject(String paramKey, Class<T> valueType) {
		return JsonHelpers.read(getString(paramKey), valueType);
	}
}