package com.dream.system.service;

import com.dream.system.entity.RoleEntity;
import com.dream.system.query.RoleQuery;
import com.dream.system.vo.DataScopeVO;
import com.dream.system.vo.RoleVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 角色信息
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface RoleService extends BaseServices<RoleEntity, RoleVO, RoleQuery> {

	void dataScope(DataScopeVO query);
}