package com.dream.system.service;

import java.io.Serializable;
import java.util.List;

import com.dream.system.entity.UserPostEntity;
import com.dream.system.query.UserPostQuery;
import com.dream.system.vo.UserPostVO;

import dream.framework.web.service.BaseService;

/**
 * 用户岗位关系
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:24:14
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface UserPostService extends BaseService<UserPostEntity, UserPostVO, UserPostQuery> {

	/**
	 * 保存或修改
	 * 
	 * @param userId 用户ID
	 * @param postIdList 岗位ID列表
	 */
	void saveOrUpdate(Long userId, List<Long> postIdList);

	/**
	 * 根据岗位id列表，删除用户岗位关系
	 * 
	 * @param postIdList 岗位id列表
	 */
	void deleteByPostIdList(List<Serializable> postIdList);

	/**
	 * 根据用户id列表，删除用户岗位关系
	 * 
	 * @param userIdList 用户id列表
	 */
	void deleteByUserIdList(List<Serializable> userIdList);

	/**
	 * 岗位ID列表
	 * 
	 * @param userId 用户ID
	 */
	List<Long> getPostIdList(Serializable userId);
}