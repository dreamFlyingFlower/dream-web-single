package com.dream.web.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.PostEntity;
import com.dream.web.query.PostQuery;

/**
 * 岗位管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:08:23
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface PostMapper extends BaseMappers<PostEntity, PostQuery> {

	Set<PostEntity> listByUserIds(List<Long> userIds);
}