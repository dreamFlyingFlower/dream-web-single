package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.UserRoleConvert;
import com.dream.web.entity.UserRoleEntity;
import com.dream.web.mapper.UserRoleMapper;
import com.dream.web.query.UserRoleQuery;
import com.dream.web.service.UserRoleService;
import com.dream.web.vo.UserRoleVO;
import com.wy.collection.ListTool;

/**
 * 账号-角色关系Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("userRoleService")
public class UserRoleServiceImpl
		extends AbstractServiceImpl<UserRoleEntity, UserRoleVO, UserRoleQuery, UserRoleConvert, UserRoleMapper>
		implements UserRoleService {

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		// 数据库角色ID列表
		List<Long> dbRoleIdList = getRoleIdList(userId);

		// 需要新增的角色ID
		Collection<Long> insertRoleIdList = ListTool.getSubtract(roleIdList, dbRoleIdList);
		if (ListTool.isNotEmpty(insertRoleIdList)) {
			List<UserRoleEntity> roleList = insertRoleIdList.stream()
					.map(roleId -> UserRoleEntity.builder().userId(userId).roleId(roleId).build())
					.collect(Collectors.toList());
			saveBatch(roleList);
		}

		// 需要删除的角色ID
		Collection<Long> deleteRoleIdList = ListTool.getSubtract(dbRoleIdList, roleIdList);
		if (ListTool.isNotEmpty(deleteRoleIdList)) {
			LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
			remove(queryWrapper.eq(UserRoleEntity::getUserId, userId).in(UserRoleEntity::getRoleId, deleteRoleIdList));
		}
	}

	@Override
	public void saveUserList(Long roleId, List<Long> userIdList) {
		List<UserRoleEntity> list =
				userIdList.stream().map(userId -> UserRoleEntity.builder().userId(userId).roleId(roleId).build())
						.collect(Collectors.toList());
		saveBatch(list);
	}

	@Override
	public void deleteByRoleIdList(List<Serializable> roleIdList) {
		remove(new LambdaQueryWrapper<UserRoleEntity>().in(UserRoleEntity::getRoleId, roleIdList));
	}

	@Override
	public void deleteByUserIdList(List<Serializable> userIdList) {
		remove(new LambdaQueryWrapper<UserRoleEntity>().in(UserRoleEntity::getUserId, userIdList));
	}

	@Override
	public void deleteByUserIdList(Long roleId, List<Long> userIdList) {
		LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
		remove(queryWrapper.eq(UserRoleEntity::getRoleId, roleId).in(UserRoleEntity::getUserId, userIdList));
	}

	@Override
	public List<Long> getRoleIdList(Serializable userId) {
		return baseMapper.getRoleIdList(userId);
	}
}