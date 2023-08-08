package com.dream.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.electric.framework.service.impl.BaseServiceImpl;
import com.electric.system.entity.SysRoleMenuEntity;
import com.electric.system.mapper.SysRoleMenuMapper;
import com.electric.system.service.SysRoleMenuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色与菜单对应关系
 * 
 * @author  
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenuQuery> implements RoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		// 数据库菜单ID列表
		List<Long> dbMenuIdList = getMenuIdList(roleId);

		// 需要新增的菜单ID
		Collection<Long> insertMenuIdList = CollUtil.subtract(menuIdList, dbMenuIdList);
		if (CollUtil.isNotEmpty(insertMenuIdList)){
			List<RoleMenuQuery> menuList = insertMenuIdList.stream().map(menuId -> {
				RoleMenuQuery entity = new RoleMenuQuery();
				entity.setMenuId(menuId);
				entity.setRoleId(roleId);
				return entity;
			}).collect(Collectors.toList());

			// 批量新增
			saveBatch(menuList);
		}

		// 需要删除的菜单ID
		Collection<Long> deleteMenuIdList = CollUtil.subtract(dbMenuIdList, menuIdList);
		if (CollUtil.isNotEmpty(deleteMenuIdList)){
			LambdaQueryWrapper<RoleMenuQuery> queryWrapper = new LambdaQueryWrapper<>();
			remove(queryWrapper.eq(RoleMenuQuery::getRoleId, roleId).in(RoleMenuQuery::getMenuId, deleteMenuIdList));
		}
	}

	@Override
	public List<Long> getMenuIdList(Long roleId){
		return baseMapper.getMenuIdList(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByRoleIdList(List<Long> roleIdList) {
		remove(new LambdaQueryWrapper<RoleMenuQuery>().in(RoleMenuQuery::getRoleId, roleIdList));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenuId(Long menuId) {
		remove(new LambdaQueryWrapper<RoleMenuQuery>().eq(RoleMenuQuery::getMenuId, menuId));
	}

}