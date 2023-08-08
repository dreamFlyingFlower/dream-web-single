package com.dream.web.service;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.ParamsEntity;
import com.dream.web.query.ParamsQuery;
import com.dream.web.vo.ParamsVO;
import com.wy.third.json.JsonTools;

/**
 * 参数管理
 *
 * @author
 */
public interface ParamsService extends BaseService<ParamsEntity, ParamsVO, ParamsQuery> {

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
		return JsonTools.parse(getString(paramKey), valueType);
	}
}