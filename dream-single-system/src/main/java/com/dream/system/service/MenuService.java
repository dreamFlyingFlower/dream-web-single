package com.dream.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dream.basic.web.service.BaseService;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.entity.MenuEntity;
import com.dream.system.query.MenuQuery;
import com.dream.system.vo.MenuVO;

/**
 * 菜单表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface MenuService extends BaseService<MenuEntity, MenuVO, MenuQuery> {

	Map<Long, MenuVO> getCaches(List<Long> menuIds);

	/**
	 * 菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<MenuVO> getMenuList(Integer type);

	/**
	 * 获取子菜单的数量
	 * 
	 * @param pid 父菜单ID
	 */
	Long getSubMenuCount(Long pid);

	/**
	 * 获取用户权限列表
	 */
	Set<String> getUserAuthority(SecurityUserDetails securityUserDetails);

	/**
	 * 用户菜单列表
	 *
	 * @param user 用户
	 * @param type 菜单类型
	 */
	List<MenuVO> getUserMenuList(SecurityUserDetails user, Integer type);

	List<MenuVO> tree(Long id);

	List<MenuVO> treeByRoleId(Long roleId);

	List<MenuVO> treeByUseId(Long userId);
}