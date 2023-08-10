package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.convert.RoleDataScopeConvert;
import com.dream.system.entity.RoleDataScopeEntity;
import com.dream.system.mapper.RoleDataScopeMapper;
import com.dream.system.query.RoleDataScopeQuery;
import com.dream.system.service.RoleDataScopeService;
import com.dream.system.vo.RoleDataScopeVO;
import com.wy.collection.ListTool;

/**
 * 角色数据权限
 *
 * @author
 */
@Service
public class RoleDataScopeServiceImpl extends AbstractServiceImpl<RoleDataScopeEntity, RoleDataScopeVO,
		RoleDataScopeQuery, RoleDataScopeConvert, RoleDataScopeMapper> implements RoleDataScopeService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> orgIdList) {
		// 数据库机构ID列表
		List<Long> dbOrgIdList = getOrgIdList(roleId);

		// 需要新增的机构ID
		Collection<Long> insertOrgIdList = ListTool.getSubtract(orgIdList, dbOrgIdList);
		if (ListTool.isNotEmpty(insertOrgIdList)) {
			List<RoleDataScopeEntity> orgList = insertOrgIdList.stream()
					.map(orgId -> RoleDataScopeEntity.builder().orgId(orgId).roleId(roleId).build())
					.collect(Collectors.toList());
			saveBatch(orgList);
		}

		// 需要删除的机构ID
		Collection<Long> deleteOrgIdList = ListTool.getSubtract(dbOrgIdList, orgIdList);
		if (ListTool.isNotEmpty(deleteOrgIdList)) {
			remove(new LambdaQueryWrapper<RoleDataScopeEntity>().eq(RoleDataScopeEntity::getRoleId, roleId)
					.in(RoleDataScopeEntity::getOrgId, deleteOrgIdList));
		}
	}

	@Override
	public List<Long> getOrgIdList(Long roleId) {
		return baseMapper.getOrgIdList(roleId);
	}

	@Override
	public void deleteByRoleIdList(List<Serializable> roleIdList) {
		remove(new LambdaQueryWrapper<RoleDataScopeEntity>().in(RoleDataScopeEntity::getRoleId, roleIdList));
	}
}