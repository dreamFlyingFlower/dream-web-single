package com.dream.system.service;

import com.dream.system.entity.RoleResourceEntity;
import com.dream.system.query.RoleResourceQuery;
import com.dream.system.vo.RoleResourceVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 角色-权限关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface RoleResourceService extends BaseServices<RoleResourceEntity, RoleResourceVO, RoleResourceQuery> {

}