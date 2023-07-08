package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.UserRole;
import com.dream.web.query.UserRoleQuery;

/**
 * 账号-角色关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface UserRoleMapper extends BaseMappers<UserRole, UserRoleQuery> {
	//
	// List<UserRole> list(Page<UserRole> page, @Param("query") UserRoleQuery
	// query);
	//
	// List<UserRole> list(@Param("query") UserRoleQuery query);
}