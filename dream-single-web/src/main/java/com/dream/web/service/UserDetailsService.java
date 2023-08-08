package com.dream.web.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.dream.web.entity.User;

public interface UserDetailsService {

	/**
	 * 获取 UserDetails 对象
	 */
	UserDetails getUserDetails(User userEntity);
}