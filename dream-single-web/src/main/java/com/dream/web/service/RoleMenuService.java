package com.dream.web.service;

import java.io.Serializable;
import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.RoleMenuEntity;
import com.dream.web.query.RoleMenuQuery;
import com.dream.web.vo.RoleMenuVO;

/**
 * 角色与菜单对应关系
 *
 * @author 飞花梦影
 * @date 2023-08-08 14:18:28
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface RoleMenuService extends BaseService<RoleMenuEntity, RoleMenuVO, RoleMenuQuery> {

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> getMenuIdList(Long roleId);

	/**
	 * 保存或修改
	 * 
	 * @param roleId 角色ID
	 * @param menuIdList 菜单ID列表
	 */
	void saveOrUpdate(Long roleId, List<Long> menuIdList);

	/**
	 * 根据角色id列表，删除角色菜单关系
	 * 
	 * @param roleIdList 角色id列表
	 */
	void deleteByRoleIdList(List<Long> roleIdList);

	/**
	 * 根据菜单id，删除角色菜单关系
	 * 
	 * @param menuId 菜单id
	 */
	void deleteByMenuId(Serializable menuId);
}