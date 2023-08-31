package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.system.convert.RoleDataScopeConvert;
import com.dream.system.entity.DataScopeEntity;
import com.dream.system.mapper.DataScopeMapper;
import com.dream.system.query.DataScopeQuery;
import com.dream.system.service.DataScopeService;
import com.dream.system.vo.DataScopeVO;
import com.wy.collection.ListHelper;

import dream.framework.mybatis.plus.service.impl.AbstractServiceImpl;

/**
 * 角色数据权限
 *
 * @author
 */
@Service
public class DataScopeServiceImpl extends AbstractServiceImpl<DataScopeEntity, DataScopeVO,
		DataScopeQuery, RoleDataScopeConvert, DataScopeMapper> implements DataScopeService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> orgIdList) {
		// 数据库机构ID列表
		List<Long> dbOrgIdList = getOrgIdList(roleId);

		// 需要新增的机构ID
		Collection<Long> insertOrgIdList = ListHelper.getSubtract(orgIdList, dbOrgIdList);
		if (ListHelper.isNotEmpty(insertOrgIdList)) {
			List<DataScopeEntity> orgList = insertOrgIdList.stream()
					.map(orgId -> DataScopeEntity.builder().orgId(orgId).roleId(roleId).build())
					.collect(Collectors.toList());
			saveBatch(orgList);
		}

		// 需要删除的机构ID
		Collection<Long> deleteOrgIdList = ListHelper.getSubtract(dbOrgIdList, orgIdList);
		if (ListHelper.isNotEmpty(deleteOrgIdList)) {
			remove(new LambdaQueryWrapper<DataScopeEntity>().eq(DataScopeEntity::getRoleId, roleId)
					.in(DataScopeEntity::getOrgId, deleteOrgIdList));
		}
	}

	@Override
	public List<Long> getOrgIdList(Long roleId) {
		return baseMapper.getOrgIdList(roleId);
	}

	@Override
	public void deleteByRoleIdList(List<Serializable> roleIdList) {
		remove(new LambdaQueryWrapper<DataScopeEntity>().in(DataScopeEntity::getRoleId, roleIdList));
	}
}