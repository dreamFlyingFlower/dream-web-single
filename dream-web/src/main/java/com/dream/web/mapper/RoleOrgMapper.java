package com.dream.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.RoleOrg;
import com.dream.framework.web.query.RoleOrgQuery;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:28:55
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleOrgMapper extends BaseMappers<RoleOrg, RoleOrgQuery> {

	/**
	 * 根据角色ID,获取机构ID列表
	 */
	List<Long> getOrgIdList(@Param("roleId") Long roleId);

	/**
	 * 获取用户的数据权限列表
	 */
	List<Long> getDataScopeList(@Param("userId") Long userId);

}