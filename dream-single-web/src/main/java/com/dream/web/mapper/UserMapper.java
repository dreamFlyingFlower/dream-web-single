package com.dream.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.UserEntity;
import com.dream.web.query.UserQuery;
import com.dream.web.vo.UserVO;

/**
 * 用户表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface UserMapper extends BaseMappers<UserEntity, UserQuery> {

	UserVO getByUsername(String username);

	UserVO getByMobile(String mobile);

	List<UserEntity> getList(Map<String, Object> params);

	UserEntity getById(@Param("id") Long id);

	List<UserEntity> getRoleUserList(Map<String, Object> params);

	Integer updateUserCert(Long id);
}