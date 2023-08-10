package com.dream.system.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.system.entity.PermissionEntity;
import com.dream.system.query.PermissionQuery;
import com.dream.system.vo.PermissionVO;

/**
 * 权限
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface PermissionService extends BaseService<PermissionEntity, PermissionVO, PermissionQuery> {

	List<PermissionEntity> getPermissionsByUserId(Long userId);
}