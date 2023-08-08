package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.ParamsEntity;
import com.dream.web.query.ParamsQuery;

/**
 * 参数管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:51:52
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface ParamsMapper extends BaseMappers<ParamsEntity, ParamsQuery> {

	default boolean isExist(String paramKey) {
		return this.exists(new QueryWrapper<ParamsEntity>().eq("param_key", paramKey));
	}
}