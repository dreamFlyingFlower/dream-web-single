package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.Role;
import com.dream.web.query.RoleQuery;

/**
 * 角色信息
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleMapper extends BaseMappers<Role, RoleQuery> {

	// List<Role> list(Page<Role> page, @Param("query") RoleQuery query);

	// List<Role> list(@Param("query") RoleQuery query);

	/**
	 * 根据用户ID,获取用户最大的数据范围
	 */
	Integer getDataScopeByUserId(@Param("userId") Long userId);
}