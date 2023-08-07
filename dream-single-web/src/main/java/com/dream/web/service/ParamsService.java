package com.dream.web.service;

import com.dream.basic.web.service.BaseService;
import com.dream.system.web.query.ParamsQuery;
import com.dream.system.web.vo.ParamsVO;
import com.dream.web.entity.ParamsEntity;

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
	int getInt(String paramKey);

	/**
	 * 根据paramKey,获取布尔值
	 *
	 * @param paramKey 参数Key
	 */
	boolean getBoolean(String paramKey);

	/**
	 * 根据paramKey,获取对象值
	 *
	 * @param paramKey 参数Key
	 * @param valueType 类型
	 */
	<T> T getJSONObject(String paramKey, Class<T> valueType);
}