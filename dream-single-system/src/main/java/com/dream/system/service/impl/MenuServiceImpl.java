package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.core.constant.ConstCore;
import com.dream.basic.core.enums.ResourceType;
import com.dream.basic.core.helper.FastjsonHelper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.framework.cache.RedisKeys;
import com.dream.framework.enums.SuperAdminEnum;
import com.dream.framework.helper.TreeHelper;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.convert.ButtonConvert;
import com.dream.system.convert.MenuConvert;
import com.dream.system.entity.ButtonEntity;
import com.dream.system.entity.MenuEntity;
import com.dream.system.entity.RoleResourceEntity;
import com.dream.system.entity.UserRoleEntity;
import com.dream.system.mapper.MenuMapper;
import com.dream.system.mapper.UserRoleMapper;
import com.dream.system.query.MenuQuery;
import com.dream.system.service.ButtonService;
import com.dream.system.service.MenuService;
import com.dream.system.service.RoleMenuService;
import com.dream.system.service.RoleResourceService;
import com.dream.system.vo.ButtonVO;
import com.dream.system.vo.MenuVO;
import com.wy.collection.ListTool;
import com.wy.collection.MapTool;
import com.wy.enums.TipEnum;
import com.wy.lang.StrTool;
import com.wy.result.ResultException;

import lombok.AllArgsConstructor;

/**
 * 菜单表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("menuService")
@AllArgsConstructor
public class MenuServiceImpl extends AbstractServiceImpl<MenuEntity, MenuVO, MenuQuery, MenuConvert, MenuMapper>
		implements MenuService {

	private RedisTemplate<String, Object> redisTemplate;

	private UserRoleMapper userRoleMapper;

	private RoleResourceService roleResourceService;

	private ButtonService buttonService;

	private ButtonConvert buttonConvert;

	private RoleMenuService roleMenuService;

	@Override
	public Boolean delete(Serializable id) {
		roleMenuService.deleteByMenuId(id);
		return removeById(id);
	}

	@Override
	public Boolean deletes(List<Serializable> ids) {
		for (Serializable id : ids) {
			delete(id);
		}
		return true;
	}

	@Override
	public Boolean edit(MenuVO dto) {
		MenuEntity entity = baseConvert.convert(dto);

		// 上级菜单不能为自己
		if (entity.getId().equals(entity.getPid())) {
			throw new ResultException("上级菜单不能为自己");
		}

		return updateById(entity);
	}

	@Override
	public Map<Long, MenuVO> getCache(String key) {
		Map<Object, Object> cacheEntries = redisTemplate.opsForHash().entries(RedisKeys.buildKey("menu"));
		if (MapTool.isNotEmpty(cacheEntries)) {
			return FastjsonHelper.parseMap(cacheEntries, new HashMap<Long, MenuVO>());
		}
		return handlerCache(null);
	}

	@Override
	public Map<Long, MenuVO> getCaches(List<Long> menuIds) {
		List<Object> cacheMenus = redisTemplate.opsForHash().multiGet(RedisKeys.buildKey("menu"),
				menuIds.stream().map(t -> t).collect(Collectors.toCollection(() -> new ArrayList<Object>())));
		if (ListTool.isNotEmpty(cacheMenus)) {
			return FastjsonHelper.parseMap(cacheMenus, new HashMap<Long, MenuVO>());
		}
		return handlerCache(menuIds);
	}

	@Override
	public List<MenuVO> getMenuList(Integer type) {
		List<MenuEntity> menuList = baseMapper.getMenuList(type);
		return TreeHelper.build(baseConvert.convertt(menuList), ConstCore.ROOT);
	}

	@Override
	public Long getSubMenuCount(Long pid) {
		return count(new LambdaQueryWrapper<MenuEntity>().eq(MenuEntity::getPid, pid));
	}

	@Override
	public Set<String> getUserAuthority(SecurityUserDetails securityUserDetails) {
		List<String> authorityList;
		if (securityUserDetails.getSuperAdmin().equals(SuperAdminEnum.YES.getCode())) {
			authorityList = baseMapper.getAuthorityList();
		} else {
			authorityList = baseMapper.getUserAuthorityList(securityUserDetails.getId());
		}
		// 用户权限列表
		Set<String> permsSet = new HashSet<>();
		for (String authority : authorityList) {
			if (StrTool.isBlank(authority)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(authority.trim().split(",")));
		}
		return permsSet;
	}

	@Override
	public List<MenuVO> getUserMenuList(SecurityUserDetails user, Integer type) {
		List<MenuEntity> menuList;
		if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getCode())) {
			menuList = baseMapper.getMenuList(type);
		} else {
			menuList = baseMapper.getUserMenuList(user.getId(), type);
		}
		return TreeHelper.build(baseConvert.convertt(menuList));
	}

	private Map<Long, MenuVO> handlerCache(List<Long> menuIds) {
		Map<Long, MenuVO> rets = Collections.emptyMap();
		List<MenuEntity> menus = ListTool.isEmpty(menuIds) ? list()
				: list(new LambdaQueryWrapper<MenuEntity>().in(MenuEntity::getId, menuIds));
		if (ListTool.isNotEmpty(menus)) {
			rets = baseConvert.convertt(menus).stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
			redisTemplate.opsForHash().putAll(RedisKeys.buildKey("menu"), rets);
		}
		return rets;
	}

	private void handlerChildren(MenuVO parent, Map<Long, List<MenuVO>> mapMenuPid2MenuDTOs,
			Map<Long, List<ButtonVO>> mapMenuId2Buttons) {
		List<MenuVO> children = mapMenuPid2MenuDTOs.get(parent.getId());
		if (ListTool.isNotEmpty(children)) {
			parent.setChildren(children);
			children.forEach(t -> t.setButtons(mapMenuId2Buttons.get(t.getId())));
			for (MenuVO menuDTO : children) {
				handlerChildren(menuDTO, mapMenuPid2MenuDTOs, mapMenuId2Buttons);
			}
		}
	}

	private List<MenuVO> handlerTree(Long roleId) {
		List<RoleResourceEntity> roleResources = roleResourceService
				.list(new LambdaQueryWrapper<RoleResourceEntity>().eq(RoleResourceEntity::getRoleId, roleId)
						.in(RoleResourceEntity::getResourceType, ResourceType.MENU, ResourceType.BUTTON));
		if (ListTool.isEmpty(roleResources)) {
			ResultException.throwException(TipEnum.TIP_ROLE_UNASSIGNED_RESOURCE);
		}

		List<Long> menuIds = new ArrayList<>();
		List<Long> buttonIds = new ArrayList<>();
		for (RoleResourceEntity roleResource : roleResources) {
			if (roleResource.getResourceType() == ResourceType.MENU.getCode()) {
				menuIds.add(roleResource.getResourceId());
			}
			if (roleResource.getResourceType() == ResourceType.BUTTON.getCode()) {
				buttonIds.add(roleResource.getResourceId());
			}
		}

		Map<Long, MenuVO> cacheMenus = getCaches(menuIds);
		if (MapTool.isEmpty(cacheMenus)) {
			ResultException.throwException(TipEnum.TIP_ROLE_UNASSIGNED_RESOURCE);
		}
		Map<Long, List<MenuVO>> mapMenuPid2MenuDTOs =
				cacheMenus.values().stream().collect(Collectors.groupingBy(k -> k.getPid()));
		Map<Long, List<ButtonVO>> mapMenuId2Buttons = buttonConvert
				.convertt(buttonService.list(new LambdaQueryWrapper<ButtonEntity>().in(ButtonEntity::getId, buttonIds)))
				.stream().collect(Collectors.groupingBy(k -> k.getMenuId()));

		List<MenuVO> rootMenus = mapMenuPid2MenuDTOs.get(0L);
		if (ListTool.isEmpty(rootMenus)) {
			ResultException.throwException("根目录不存在,请重新分配菜单");
		}
		handlerChildren(rootMenus.get(0), mapMenuPid2MenuDTOs, mapMenuId2Buttons);
		return rootMenus;
	}

	@Override
	public List<MenuVO> tree(Long id) {
		Map<Long, MenuVO> cacheMenus = getCache(null);
		if (MapTool.isEmpty(cacheMenus)) {
			ResultException.throwException(TipEnum.TIP_ROLE_UNASSIGNED_RESOURCE);
		}
		Map<Long, List<MenuVO>> mapMenuPid2MenuDTOs =
				cacheMenus.values().stream().collect(Collectors.groupingBy(k -> k.getPid()));
		Map<Long, List<ButtonVO>> mapMenuId2Buttons =
				buttonConvert.convertt(buttonService.list(new LambdaQueryWrapper<ButtonEntity>())).stream()
						.collect(Collectors.groupingBy(k -> k.getMenuId()));
		handlerChildren(cacheMenus.get(id), mapMenuPid2MenuDTOs, mapMenuId2Buttons);
		return Arrays.asList(cacheMenus.get(id));
	}

	@Override
	public List<MenuVO> treeByRoleId(Long roleId) {
		return handlerTree(roleId);
	}

	@Override
	public List<MenuVO> treeByUseId(Long userId) {
		List<UserRoleEntity> userRoles = userRoleMapper.selectList(
				new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userId).last(" limit 1"));
		if (ListTool.isEmpty(userRoles)) {
			ResultException.throwException(TipEnum.TIP_USER_NOT_DISTRIBUTE_ROLE);
		}
		// FIXME 若多角色,可从登录缓存中取得正在使用的角色 (飞花梦影,2022-09-05,[2022-09-05])
		UserRoleEntity userRole = userRoles.get(0);
		return handlerTree(userRole.getRoleId());
	}
}