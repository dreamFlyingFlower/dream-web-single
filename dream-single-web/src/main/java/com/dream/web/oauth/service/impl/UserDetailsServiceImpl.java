package com.dream.web.oauth.service.impl;

import lombok.AllArgsConstructor;

import com.electric.system.entity.SysUserEntity;
import com.electric.system.mapper.SysUserMapper;
import com.electric.system.service.SysUserDetailsService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 账号登录 UserDetailsService
 *
 * @author
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserDetailsService sysUserDetailsService;

	private final SysUserMapper sysUserDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserEntity userEntity = sysUserDao.getByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}

		return sysUserDetailsService.getUserDetails(userEntity);
	}

}