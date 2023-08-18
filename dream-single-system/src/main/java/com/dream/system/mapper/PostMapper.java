package com.dream.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.entity.PostEntity;
import com.dream.system.query.PostQuery;
import com.dream.system.vo.PostVO;

import dream.framework.web.mapper.BaseMappers;

/**
 * 岗位管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:08:23
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface PostMapper extends BaseMappers<PostEntity, PostVO, PostQuery> {

	Set<PostEntity> listByUserIds(List<Long> userIds);
}