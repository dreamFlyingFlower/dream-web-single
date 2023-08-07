package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.PostQuery;
import com.dream.system.web.vo.PostVO;
import com.dream.web.convert.PostConvert;
import com.dream.web.entity.PostEntity;
import com.dream.web.mapper.PostMapper;
import com.dream.web.service.PostService;
import com.dream.web.service.UserPostService;

/**
 * 岗位管理
 * 
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:12:50
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class PostServiceImpl extends AbstractServiceImpl<PostEntity, PostVO, PostQuery, PostConvert, PostMapper>
		implements PostService {

	private UserPostService sysUserPostService;

	@Override
	public Boolean deleteById(Serializable id) {
		// 删除岗位
		removeById(id);
		// 删除岗位用户关系
		sysUserPostService.deleteByPostIdList(Arrays.asList(id));
		return true;
	}

	@Override
	public Boolean deleteByIds(List<Serializable> idList) {
		// 删除岗位
		removeByIds(idList);
		// 删除岗位用户关系
		sysUserPostService.deleteByPostIdList(idList);
		return true;
	}
}