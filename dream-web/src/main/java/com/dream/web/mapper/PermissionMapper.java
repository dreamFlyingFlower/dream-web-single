package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.framework.web.query.PermissionQuery;
import com.dream.web.entity.Permission;

/**
 * 权限
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface PermissionMapper extends BaseMappers<Permission, PermissionQuery> {

	// List<Permission> list(Page<Permission> page, @Param("query") PermissionQuery
	// query);

	// List<Permission> list(@Param("query") PermissionQuery query);
}