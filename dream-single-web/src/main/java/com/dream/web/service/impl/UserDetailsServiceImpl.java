package com.dream.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dream.system.enums.DataScopeEnum;
import com.dream.system.enums.UserStatus;
import com.dream.web.entity.UserEntity;
import com.dream.web.mapper.RoleDataScopeMapper;
import com.dream.web.mapper.RoleMapper;
import com.dream.web.security.UserDetail;
import com.dream.web.service.MenuService;
import com.dream.web.service.OrgService;
import com.dream.web.service.UserDetailsService;

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
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MenuService sysMenuService;

	private final OrgService sysOrgService;

	private final RoleMapper sysRoleDao;

	private final RoleDataScopeMapper sysRoleDataScopeDao;

	@Override
	public UserDetails getUserDetails(UserEntity userEntity) {
		UserDetail userDetail = new UserDetail();
		BeanUtils.copyProperties(userEntity, userDetail);

		// 账号不可用
		if (userEntity.getStatus() == UserStatus.DISABLE.getValue()) {
			userDetail.setEnabled(false);
		}

		// 数据权限范围
		List<Long> dataScopeList = getDataScope(userDetail);
		userDetail.setDataScopeList(dataScopeList);

		// 用户权限列表
		Set<String> authoritySet = sysMenuService.getUserAuthority(userDetail);
		userDetail.setAuthoritySet(authoritySet);

		return userDetail;
	}

	private List<Long> getDataScope(UserDetail userDetail) {
		Integer dataScope = sysRoleDao.getDataScopeByUserId(userDetail.getId());
		if (dataScope == null) {
			return new ArrayList<>();
		}

		if (dataScope.equals(DataScopeEnum.ALL.getValue())) {
			// 全部数据权限，则返回null
			return null;
		} else if (dataScope.equals(DataScopeEnum.ORG_AND_CHILD.getValue())) {
			// 本机构及子机构数据
			List<Long> dataScopeList = sysOrgService.getSubOrgIdList(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.ORG_ONLY.getValue())) {
			// 本机构数据
			List<Long> dataScopeList = new ArrayList<>();
			dataScopeList.add(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.CUSTOM.getValue())) {
			// 自定义数据权限范围
			return sysRoleDataScopeDao.getDataScopeList(userDetail.getId());
		}

		return new ArrayList<>();
	}
}