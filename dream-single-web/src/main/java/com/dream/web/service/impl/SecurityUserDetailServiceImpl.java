package com.dream.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.dream.web.entity.PermissionEntity;
import com.dream.web.service.PermissionService;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserVO;
import com.wy.collection.ListTool;

/**
 * 
 *
 * @author 飞花梦影
 * @date 2022-06-17 16:44:30
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Service
public class SecurityUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserVO user = userService.getByUsername(username);
		if (user == null) {
			return null;
		}
		// 获取权限
		List<PermissionEntity> permissions = permissionService.getPermissionsByUserId(user.getId());
		List<String> codes = permissions.stream().map(PermissionEntity::getPermissionCode).collect(Collectors.toList());
		String[] authorities = null;
		if (ListTool.isNotEmpty(codes)) {
			authorities = new String[codes.size()];
			codes.toArray(authorities);
		}
		// 身份令牌
		String principal = JSON.toJSONString(user);
		return org.springframework.security.core.userdetails.User.withUsername(principal).password(user.getPassword())
				.authorities(authorities).build();
	}
}