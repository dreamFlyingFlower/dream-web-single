package com.dream.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.framework.enums.DataScopeEnum;
import com.dream.framework.enums.UserStatus;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.mapper.RoleDataScopeMapper;
import com.dream.system.mapper.RoleMapper;
import com.dream.system.service.MenuService;
import com.dream.system.service.OrgService;
import com.dream.system.service.UserDetailService;
import com.dream.system.service.UserService;
import com.dream.system.vo.UserVO;

import lombok.AllArgsConstructor;

/**
 * 用户 UserDetails 信息
 *
 * @author 飞花梦影
 * @date 2023-08-08 23:35:42
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class UserDetailServiceImpl
		implements UserDetailService, org.springframework.security.core.userdetails.UserDetailsService {

	private final MenuService menuService;

	private final OrgService orgService;

	private final RoleMapper roleMapper;

	private final RoleDataScopeMapper roleDataScopeMapper;

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO usesrVo = userService.getByUsername(username);
		if (usesrVo == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}
		getUserDetails(usesrVo);
		return usesrVo;
	}

	@Override
	public void getUserDetails(UserVO userVO) {
		SecurityUserDetails securityUserDetails = new SecurityUserDetails();
		BeanUtils.copyProperties(userVO, securityUserDetails);

		// 账号不可用
		if (userVO.getStatus() == UserStatus.DISABLE.getCode()) {
			userVO.setEnabled(false);
		}

		// 数据权限范围
		List<Long> dataScopeList = getDataScope(userVO);
		userVO.setDataScopeList(dataScopeList);

		// 用户权限列表
		Set<String> authoritySet = menuService.getUserAuthority(securityUserDetails);
		userVO.setAuthoritySet(authoritySet);
	}

	private List<Long> getDataScope(UserVO userDetail) {
		Integer dataScope = roleMapper.getDataScopeByUserId(userDetail.getId());
		if (dataScope == null) {
			return new ArrayList<>();
		}

		if (dataScope.equals(DataScopeEnum.ALL.getCode())) {
			// 全部数据权限,则返回null
			return null;
		} else if (dataScope.equals(DataScopeEnum.ORG_AND_CHILD.getCode())) {
			// 本机构及子机构数据
			List<Long> dataScopeList = orgService.getSubOrgIdList(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(roleDataScopeMapper.getDataScopeList(userDetail.getId()));
			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.ORG_ONLY.getCode())) {
			// 本机构数据
			List<Long> dataScopeList = new ArrayList<>();
			dataScopeList.add(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(roleDataScopeMapper.getDataScopeList(userDetail.getId()));
			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.CUSTOM.getCode())) {
			// 自定义数据权限范围
			return roleDataScopeMapper.getDataScopeList(userDetail.getId());
		}
		return new ArrayList<>();
	}
}