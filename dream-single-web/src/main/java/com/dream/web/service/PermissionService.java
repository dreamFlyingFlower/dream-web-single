package com.dream.web.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.system.web.query.PermissionQuery;
import com.dream.system.web.vo.PermissionVO;
import com.dream.web.entity.Permission;

/**
 * 权限
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface PermissionService extends BaseService<Permission, PermissionVO, PermissionQuery> {

	List<Permission> getPermissionsByUserId(Long userId);
}