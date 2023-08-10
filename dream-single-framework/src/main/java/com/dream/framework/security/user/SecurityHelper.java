package com.dream.framework.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户
 *
 * @author 飞花梦影
 * @date 2023-08-09 11:22:40
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class SecurityHelper {

	/**
	 * 获取用户信息
	 */
	public static SecurityUserDetails getUser() {
		SecurityUserDetails user;
		try {
			user = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return new SecurityUserDetails();
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