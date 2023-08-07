package com.dream.system.web.vo;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证用户信息
 *
 * @author 飞花梦影
 * @date 2023-07-08 17:04:22
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class SecurityUser {

	/**
	 * 获取用户信息
	 */
	public static UserVO getUser() {
		UserVO user;
		try {
			user = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new UserVO();
		}
		return user;
	}

	/**
	 * 获取用户ID
	 */
	public static Long getUserId() {
		return getUser().getId();
	}
}