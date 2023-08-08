package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.RoleMenuConvert;
import com.dream.web.entity.RoleMenuEntity;
import com.dream.web.mapper.RoleMenuMapper;
import com.dream.web.query.RoleMenuQuery;
import com.dream.web.service.RoleMenuService;
import com.dream.web.vo.RoleMenuVO;
import com.wy.collection.ListTool;

/**
 * 角色与菜单对应关系
 *
 * @author 飞花梦影
 * @date 2023-08-08 23:24:31
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class RoleMenuServiceImpl
		extends AbstractServiceImpl<RoleMenuEntity, RoleMenuVO, RoleMenuQuery, RoleMenuConvert, RoleMenuMapper>
		implements RoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		// 数据库菜单ID列表
		List<Long> dbMenuIdList = getMenuIdList(roleId);

		// 需要新增的菜单ID
		Collection<Long> insertMenuIdList = ListTool.getSubtract(menuIdList, dbMenuIdList);
		if (ListTool.isNotEmpty(insertMenuIdList)) {
			List<RoleMenuEntity> menuList = insertMenuIdList.stream()
					.map(menuId -> RoleMenuEntity.builder().menuId(menuId).roleId(roleId).build())
					.collect(Collectors.toList());
			saveBatch(menuList);
		}

		// 需要删除的菜单ID
		Collection<Long> deleteMenuIdList = ListTool.getSubtract(dbMenuIdList, menuIdList);
		if (ListTool.isNotEmpty(deleteMenuIdList)) {
			LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
			remove(queryWrapper.eq(RoleMenuEntity::getRoleId, roleId).in(RoleMenuEntity::getMenuId, deleteMenuIdList));
		}
	}

	@Override
	public List<Long> getMenuIdList(Long roleId) {
		return baseMapper.getMenuIdList(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByRoleIdList(List<Serializable> roleIdList) {
		remove(new LambdaQueryWrapper<RoleMenuEntity>().in(RoleMenuEntity::getRoleId, roleIdList));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenuId(Serializable menuId) {
		remove(new LambdaQueryWrapper<RoleMenuEntity>().eq(RoleMenuEntity::getMenuId, menuId));
	}
}