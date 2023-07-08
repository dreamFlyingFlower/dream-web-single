package com.dream.web.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnifiedUserDetails implements UserDetails {

	private static final long serialVersionUID = 3957586021470480642L;

	/**
	 * 用户的授权集合
	 */
	@Builder.Default
	protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	private String username;

	private String password;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 租户id
	 */
	private String tenantId;

	/**
	 * 部门id
	 */
	private String departId;

	/**
	 * 用户的角色权限集合,key为角色,value为角色下权限集合
	 */
	@Builder.Default
	private Map<String, List<String>> userAuthorities = new HashMap<>();

	/**
	 * 用户附加信息,json字符串,统一认证透传
	 */
	@Builder.Default
	private Map<String, Object> payload = new HashMap<>();

	public UnifiedUserDetails(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UnifiedUserDetails(String username, String password, List<GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		grantedAuthorities.addAll(authorities);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	/* 账户是否未过期 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* 账户是否未锁定 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* 密码是否未过期 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* 账户是否启用,默认true (启用) */
	@Override
	public boolean isEnabled() {
		return true;
	}
}