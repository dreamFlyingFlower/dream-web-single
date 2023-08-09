package com.dream.web.oauth.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.web.oauth.mobile.MobileUserDetailsService;
import com.dream.web.service.UserDetailService;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserVO;

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