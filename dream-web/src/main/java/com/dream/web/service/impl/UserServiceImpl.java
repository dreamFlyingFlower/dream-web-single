package com.dream.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.UserConvert;
import com.dream.web.entity.User;
import com.dream.web.enums.DataScopeEnum;
import com.dream.web.enums.UserStatus;
import com.dream.web.mapper.RoleMapper;
import com.dream.web.mapper.RoleOrgMapper;
import com.dream.web.mapper.UserMapper;
import com.dream.web.query.UserQuery;
import com.dream.web.service.MenuService;
import com.dream.web.service.OrgService;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserVO;

/**
 * 用户Service
 * 
 * @author 飞花梦影
 * @date 2023-07-08 22:48:29
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User, UserVO, UserQuery, UserConvert, UserMapper>
		implements UserService, UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private OrgService orgService;

	@Autowired
	private RoleOrgMapper roleOrgMapper;

	@Override
	public UserVO getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO usesrVo = userMapper.getByUsername(username);
		if (usesrVo == null) {
			throw new UsernameNotFoundException("用户名或密码错误");
		}
		getUserDetails(usesrVo);
		return usesrVo;
	}

	@Override
	public void getUserDetails(UserVO usesrVo) {
		// 账号不可用
		if (usesrVo.getStatus() == UserStatus.DISABLE.getValue()) {
			usesrVo.setEnabled(false);
		}

		// 数据权限范围
		List<Long> dataScopeList = getDataScope(usesrVo);
		usesrVo.setDataScopeList(dataScopeList);

		// 用户权限列表
		Set<String> authoritySet = menuService.getUserAuthority(usesrVo);
		usesrVo.setAuthoritySet(authoritySet);
	}

	private List<Long> getDataScope(UserVO userDetail) {
		Integer dataScope = roleMapper.getDataScopeByUserId(userDetail.getId());
		if (dataScope == null) {
			return new ArrayList<>();
		}

		if (dataScope.equals(DataScopeEnum.ALL.getValue())) {
			// 全部数据权限,则返回null
			return null;
		} else if (dataScope.equals(DataScopeEnum.ORG_AND_CHILD.getValue())) {
			// 本机构及子机构数据
			List<Long> dataScopeList = orgService.getSubOrgIdList(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(roleOrgMapper.getDataScopeList(userDetail.getId()));
			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.ORG_ONLY.getValue())) {
			// 本机构数据
			List<Long> dataScopeList = new ArrayList<>();
			dataScopeList.add(userDetail.getOrgId());
			// 自定义数据权限范围
			dataScopeList.addAll(roleOrgMapper.getDataScopeList(userDetail.getId()));
			return dataScopeList;
		} else if (dataScope.equals(DataScopeEnum.CUSTOM.getValue())) {
			// 自定义数据权限范围
			return roleOrgMapper.getDataScopeList(userDetail.getId());
		}

		return new ArrayList<>();
	}
}