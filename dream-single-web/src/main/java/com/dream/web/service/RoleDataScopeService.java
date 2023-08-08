package com.dream.web.service;

import java.io.Serializable;
import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.RoleDataScopeEntity;
import com.dream.web.query.RoleDataScopeQuery;
import com.dream.web.vo.RoleDataScopeVO;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-08-08 14:17:39
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface RoleDataScopeService extends BaseService<RoleDataScopeEntity, RoleDataScopeVO, RoleDataScopeQuery> {

	/**
	 * 保存或修改
	 * 
	 * @param roleId 角色ID
	 * @param orgIdList 机构ID列表
	 */
	void saveOrUpdate(Long roleId, List<Long> orgIdList);

	/**
	 * 根据角色ID，获取机构ID列表
	 */
	List<Long> getOrgIdList(Long roleId);

	/**
	 * 根据角色id列表，删除角色数据权限关系
	 * 
	 * @param roleIdList 角色id列表
	 */
	void deleteByRoleIdList(List<Serializable> roleIdList);
}