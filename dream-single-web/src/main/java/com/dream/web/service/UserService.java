package com.dream.web.service;

import com.dream.basic.web.service.BaseService;
import com.dream.system.web.query.UserQuery;
import com.dream.system.web.vo.AccountVO;
import com.dream.system.web.vo.LoginAccountVO;
import com.dream.system.web.vo.UserVO;
import com.dream.web.entity.User;

/**
 * 用户表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface UserService extends BaseService<User, UserVO, UserQuery> {

	UserVO getByMobile(String mobile);

	UserVO getByUsername(String username);

	void getUserDetails(UserVO usesrVo);

	AccountVO login(LoginAccountVO loginAccountVO);
}