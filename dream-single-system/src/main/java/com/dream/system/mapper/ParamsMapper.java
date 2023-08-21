package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.system.entity.ParamsEntity;
import com.dream.system.query.ParamsQuery;
import com.dream.system.vo.ParamsVO;

import dream.framework.mybatis.plus.mapper.BaseMappers;

/**
 * 参数管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:51:52
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface ParamsMapper extends BaseMappers<ParamsEntity, ParamsVO, ParamsQuery> {

	default boolean isExist(String paramKey) {
		return this.exists(new QueryWrapper<ParamsEntity>().eq("param_key", paramKey));
	}
}