package com.dream.web.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.web.oauth.mobile.MobileUserDetailsService;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserVO;

/**
 * 手机验证码登录
 *
 * @author 飞花梦影
 * @date 2023-07-08 22:48:46
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
		UserVO usesrVo = userService.getByMobile(mobile);
		if (usesrVo == null) {
			throw new UsernameNotFoundException("手机号或验证码错误");
		}
		userService.getUserDetails(usesrVo);
		return usesrVo;
	}
}