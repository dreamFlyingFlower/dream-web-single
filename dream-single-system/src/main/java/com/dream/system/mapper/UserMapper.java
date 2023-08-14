package com.dream.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.entity.UserEntity;
import com.dream.system.query.UserQuery;
import com.dream.system.query.UserRoleQuery;
import com.dream.system.vo.UserVO;

/**
 * 用户表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface UserMapper extends BaseMappers<UserEntity, UserVO, UserQuery> {

	List<UserEntity> getList(Map<String, Object> params);

	UserEntity getById(@Param("id") Long id);

	List<UserEntity> getRoleUserList(UserRoleQuery query);

	Integer updateUserCert(Long id);
}