package com.dream.system.oauth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.system.mapper.UserMapper;
import com.dream.system.service.UserDetailService;
import com.dream.system.vo.UserVO;

import lombok.AllArgsConstructor;

/**
 * 账号登录 UserDetailsService
 *
 * @author 飞花梦影
 * @date 2023-08-09 15:20:53
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDetailService userDetailService;

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userMapper.getByUsername(username);
		if (userVO == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}
		userDetailService.getUserDetails(userVO);
		return userVO;
	}

}