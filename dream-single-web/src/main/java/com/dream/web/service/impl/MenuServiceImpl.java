package com.dream.web.service.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.core.constant.ConstCore;
import com.dream.basic.core.enums.ResourceType;
import com.dream.basic.core.helper.FastjsonHelper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.enums.SuperAdminEnum;
import com.dream.system.utils.TreeUtils;
import com.dream.web.cache.RedisKeys;
import com.dream.web.convert.ButtonConvert;
import com.dream.web.convert.MenuConvert;
import com.dream.web.entity.Button;
import com.dream.web.entity.MenuEntity;
import com.dream.web.entity.RoleResource;
import com.dream.web.entity.UserRole;
import com.dream.web.mapper.MenuMapper;
import com.dream.web.mapper.UserRoleMapper;
import com.dream.web.query.MenuQuery;
import com.dream.web.security.UserDetail;
import com.dream.web.service.ButtonService;
import com.dream.web.service.MenuService;
import com.dream.web.service.RoleMenuService;
import com.dream.web.service.RoleResourceService;
import com.dream.web.vo.ButtonVO;
import com.dream.web.vo.MenuVO;
import com.dream.web.vo.UserVO;
import com.wy.collection.ListTool;
import com.wy.collection.MapTool;
import com.wy.enums.TipEnum;
import com.wy.lang.StrTool;
import com.wy.result.ResultException;

/**
 * 菜单表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl<MenuEntity, MenuVO, MenuQuery, MenuConvert, MenuMapper>
		implements MenuService {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleResourceService roleResourceService;

	@Autowired
	private ButtonService buttonService;

	@Autowired
	private ButtonConvert buttonConvert;

	@Autowired
	private RoleMenuService roleMenuService;

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

	@Override
	public List<MenuVO> tree(Long id) {
		Map<Long, MenuVO> cacheMenus = getCache(null);
		if (MapTool.isEmpty(cacheMenus)) {
			ResultException.throwException(TipEnum.TIP_ROLE_UNASSIGNED_RESOURCE);
		}
		Map<Long, List<MenuVO>> mapMenuPid2MenuDTOs =
				cacheMenus.values().stream().collect(Collectors.groupingBy(k -> k.getPid()));
		Map<Long, List<ButtonVO>> mapMenuId2Buttons =
				buttonConvert.convertt(buttonService.list(new LambdaQueryWrapper<Button>())).stream()
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
		List<UserRole> userRoles = userRoleMapper
				.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId).last(" limit 1"));
		if (ListTool.isEmpty(userRoles)) {
			ResultException.throwException(TipEnum.TIP_USER_NOT_DISTRIBUTE_ROLE);
		}
		// FIXME 若多角色,可从登录缓存中取得正在使用的角色 (飞花梦影,2022-09-05,[2022-09-05])
		UserRole userRole = userRoles.get(0);
		return handlerTree(userRole.getRoleId());
	}

	private List<MenuVO> handlerTree(Long roleId) {
		List<RoleResource> roleResources =
				roleResourceService.list(new LambdaQueryWrapper<RoleResource>().eq(RoleResource::getRoleId, roleId)
						.in(RoleResource::getResourceType, ResourceType.MENU, ResourceType.BUTTON));
		if (ListTool.isEmpty(roleResources)) {
			ResultException.throwException(TipEnum.TIP_ROLE_UNASSIGNED_RESOURCE);
		}

		List<Long> menuIds = new ArrayList<>();
		List<Long> buttonIds = new ArrayList<>();
		for (RoleResource roleResource : roleResources) {
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
		Map<Long,
				List<ButtonVO>> mapMenuId2Buttons = buttonConvert
						.convertt(buttonService.list(new LambdaQueryWrapper<Button>().in(Button::getId, buttonIds)))
						.stream().collect(Collectors.groupingBy(k -> k.getMenuId()));

		List<MenuVO> rootMenus = mapMenuPid2MenuDTOs.get(0L);
		if (ListTool.isEmpty(rootMenus)) {
			ResultException.throwException("根目录不存在,请重新分配菜单");
		}
		handlerChildren(rootMenus.get(0), mapMenuPid2MenuDTOs, mapMenuId2Buttons);
		return rootMenus;
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

	@Override
	public Set<String> getUserAuthority(UserVO usesrVo) {
		// 系统管理员,拥有最高权限
		List<String> authorityList;
		if (usesrVo.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
			authorityList = baseMapper.getAuthorityList();
		} else {
			authorityList = baseMapper.getUserAuthorityList(usesrVo.getId());
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
	public List<MenuVO> getMenuList(Integer type) {
		List<MenuEntity> menuList = baseMapper.getMenuList(type);
		return TreeUtils.build(baseConvert.convertt(menuList), ConstCore.ROOT);
	}

	@Override
	public List<MenuVO> getUserMenuList(UserDetail user, Integer type) {
		List<MenuEntity> menuList;
		if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
			menuList = baseMapper.getMenuList(type);
		} else {
			menuList = baseMapper.getUserMenuList(user.getId(), type);
		}
		return TreeUtils.build(baseConvert.convertt(menuList));
	}

	@Override
	public Long getSubMenuCount(Long pid) {
		return count(new LambdaQueryWrapper<MenuEntity>().eq(MenuEntity::getPid, pid));
	}

	@Override
	public Set<String> getUserAuthority(UserDetail user) {
		List<String> authorityList;
		if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
			authorityList = baseMapper.getAuthorityList();
		} else {
			authorityList = baseMapper.getUserAuthorityList(user.getId());
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
	public Boolean edit(MenuVO dto) {
		MenuEntity entity = baseConvert.convert(dto);

		// 上级菜单不能为自己
		if (entity.getId().equals(entity.getPid())) {
			throw new ResultException("上级菜单不能为自己");
		}

		return updateById(entity);
	}

	@Override
	public Boolean deleteById(Serializable id) {
		roleMenuService.deleteByMenuId(id);
		return removeById(id);
	}

	@Override
	public Boolean deleteByIds(List<Serializable> ids) {
		for (Serializable id : ids) {
			deleteById(id);
		}
		return true;
	}
}