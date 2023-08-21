package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.system.entity.RoleEntity;
import com.dream.system.query.RoleQuery;
import com.dream.system.vo.RoleVO;

import dream.framework.mybatis.plus.mapper.BaseMappers;

/**
 * 角色信息
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleMapper extends BaseMappers<RoleEntity, RoleVO, RoleQuery> {

	/**
	 * 根据用户ID,获取用户最大的数据范围
	 */
	Integer getDataScopeByUserId(@Param("userId") Long userId);
}