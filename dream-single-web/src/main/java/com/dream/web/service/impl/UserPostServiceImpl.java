package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.UserPostConvert;
import com.dream.web.entity.UserPostEntity;
import com.dream.web.mapper.UserPostMapper;
import com.dream.web.query.UserPostQuery;
import com.dream.web.service.UserPostService;
import com.dream.web.vo.UserPostVO;
import com.wy.collection.ListTool;

/**
 * 用户岗位关系
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:25:59
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class UserPostServiceImpl
		extends AbstractServiceImpl<UserPostEntity, UserPostVO, UserPostQuery, UserPostConvert, UserPostMapper>
		implements UserPostService {

	@Override
	public void saveOrUpdate(Long userId, List<Long> postIdList) {
		// 数据库岗位ID列表
		List<Long> dbPostIdList = getPostIdList(userId);

		// 需要新增的岗位ID
		Collection<Long> insertPostIdList = ListTool.getSubtract(postIdList, dbPostIdList);
		if (ListTool.isNotEmpty(insertPostIdList)) {
			List<UserPostEntity> postList = insertPostIdList.stream().map(postId -> {
				UserPostEntity entity = new UserPostEntity();
				entity.setUserId(userId);
				entity.setPostId(postId);
				return entity;
			}).collect(Collectors.toList());

			// 批量新增
			saveBatch(postList);
		}

		// 需要删除的岗位ID
		Collection<Long> deletePostIdList = ListTool.getSubtract(dbPostIdList, postIdList);
		if (ListTool.isNotEmpty(deletePostIdList)) {
			LambdaQueryWrapper<UserPostEntity> queryWrapper = new LambdaQueryWrapper<>();
			remove(queryWrapper.eq(UserPostEntity::getUserId, userId).in(UserPostEntity::getPostId, deletePostIdList));
		}
	}

	@Override
	public void deleteByPostIdList(List<Serializable> postIdList) {
		remove(new LambdaQueryWrapper<UserPostEntity>().in(UserPostEntity::getPostId, postIdList));
	}

	@Override
	public void deleteByUserIdList(List<Serializable> userIdList) {
		remove(new LambdaQueryWrapper<UserPostEntity>().in(UserPostEntity::getUserId, userIdList));
	}

	@Override
	public List<Long> getPostIdList(Serializable userId) {
		return baseMapper.getPostIdList(userId);
	}
}