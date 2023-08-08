package com.dream.web.service;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.Role;
import com.dream.web.query.RoleQuery;
import com.dream.web.vo.RoleDataScopeVO;
import com.dream.web.vo.RoleVO;

/**
 * 角色信息
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface RoleService extends BaseService<Role, RoleVO, RoleQuery> {

	void dataScope(RoleDataScopeVO query);
}