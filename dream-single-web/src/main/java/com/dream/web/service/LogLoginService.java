package com.dream.web.service;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.LogLoginEntity;
import com.dream.web.query.LogLoginQuery;
import com.dream.web.vo.LogLoginVO;

/**
 * 登录日志
 *
 * @author 飞花梦影
 * @date 2023-08-08 13:35:46
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface LogLoginService extends BaseService<LogLoginEntity, LogLoginVO, LogLoginQuery> {

	/**
	 * 保存登录日志
	 *
	 * @param username 用户名
	 * @param status 登录状态
	 * @param operation 操作信息
	 */
	void save(String username, Integer status, Integer operation);

	/**
	 * 导出登录日志
	 */
	void export();
}