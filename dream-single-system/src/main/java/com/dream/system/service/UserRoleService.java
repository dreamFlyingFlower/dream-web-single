package com.dream.system.service;

import java.io.Serializable;
import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.system.entity.UserRoleEntity;
import com.dream.system.query.UserRoleQuery;
import com.dream.system.vo.UserRoleVO;

/**
 * 账号-角色关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface UserRoleService extends BaseService<UserRoleEntity, UserRoleVO, UserRoleQuery> {

	/**
	 * 保存或修改
	 * 
	 * @param userId 用户ID
	 * @param roleIdList 角色ID列表
	 */
	void saveOrUpdate(Long userId, List<Long> roleIdList);

	/**
	 * 分配角色给用户列表
	 * 
	 * @param roleId 角色ID
	 * @param userIdList 用户ID列表
	 */
	void saveUserList(Long roleId, List<Long> userIdList);

	/**
	 * 根据角色id列表,删除用户角色关系
	 * 
	 * @param roleIdList 角色id
	 */
	void deleteByRoleIdList(List<Serializable> roleIdList);

	/**
	 * 根据用户id列表,删除用户角色关系
	 * 
	 * @param userIdList 用户id列表
	 */
	void deleteByUserIdList(List<Serializable> userIdList);

	/**
	 * 根据角色id、用户id列表,删除用户角色关系
	 * 
	 * @param roleId 角色id
	 * @param userIdList 用户id列表
	 */
	void deleteByUserIdList(Long roleId, List<Long> userIdList);

	/**
	 * 角色ID列表
	 * 
	 * @param userId 用户ID
	 */
	List<Long> getRoleIdList(Serializable userId);
}