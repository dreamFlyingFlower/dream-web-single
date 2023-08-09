package com.dream.web.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.UserRoleEntity;
import com.dream.web.query.UserRoleQuery;
import com.dream.web.vo.UserRoleVO;

/**
 * 账号-角色关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface UserRoleMapper extends BaseMappers<UserRoleEntity, UserRoleVO, UserRoleQuery> {

	/**
	 * 角色ID列表
	 * 
	 * @param userId 用户ID
	 *
	 * @return 返回角色ID列表
	 */
	List<Long> getRoleIdList(@Param("userId") Serializable userId);
}