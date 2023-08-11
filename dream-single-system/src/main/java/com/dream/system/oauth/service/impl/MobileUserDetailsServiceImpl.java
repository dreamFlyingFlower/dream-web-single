package com.dream.system.oauth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.framework.security.mobile.service.MobileUserDetailsService;
import com.dream.system.service.UserDetailService;
import com.dream.system.service.UserService;
import com.dream.system.vo.UserVO;

import lombok.AllArgsConstructor;

/**
 * 手机验证码登录
 *
 * @author 飞花梦影
 * @date 2023-07-08 22:48:46
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {

	private final UserDetailService userDetailsService;

	private final UserService userService;

	@Override
	public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
		UserVO userVo = userService.getByMobile(mobile);
		if (userVo == null) {
			throw new UsernameNotFoundException("手机号或验证码错误");
		}
		userDetailsService.getUserDetails(userVo);
		return userVo;
	}
}