package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.User;
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
public interface UserMapper extends BaseMappers<User, UserQuery> {

	UserVO getByUsername(String username);

	UserVO getByMobile(String mobile);

	// List<User> list(Page<User> page, @Param("query") UserQuery query);
	//
	// List<User> list(@Param("query") UserQuery query);
}