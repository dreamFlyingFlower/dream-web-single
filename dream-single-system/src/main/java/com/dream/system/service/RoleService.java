package com.dream.system.service;

import com.dream.basic.web.service.BaseService;
import com.dream.system.entity.RoleEntity;
import com.dream.system.query.RoleQuery;
import com.dream.system.vo.RoleDataScopeVO;
import com.dream.system.vo.RoleVO;

/**
 * 角色信息
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface RoleService extends BaseService<RoleEntity, RoleVO, RoleQuery> {

	void dataScope(RoleDataScopeVO query);
}