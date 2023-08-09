package com.dream.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.RoleDataScopeEntity;
import com.dream.web.query.RoleDataScopeQuery;
import com.dream.web.vo.RoleDataScopeVO;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-08-08 10:28:03
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleDataScopeMapper extends BaseMappers<RoleDataScopeEntity, RoleDataScopeVO, RoleDataScopeQuery> {

	/**
	 * 根据角色ID,获取机构ID列表
	 */
	List<Long> getOrgIdList(@Param("roleId") Long roleId);

	/**
	 * 获取用户的数据权限列表
	 */
	List<Long> getDataScopeList(@Param("userId") Long userId);
}