package com.dream.system.service;

import com.dream.framework.security.user.SecurityUserDetails;

public interface UserDetailService {

	/**
	 * 获取 UserDetails 对象
	 */
	void getUserDetails(SecurityUserDetails securityUserDetails);
}