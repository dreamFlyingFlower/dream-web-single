package com.dream.web.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.dream.web.entity.UserEntity;

public interface UserDetailsService {

	/**
	 * 获取 UserDetails 对象
	 */
	UserDetails getUserDetails(UserEntity userEntity);
}