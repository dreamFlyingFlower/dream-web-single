package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.DictEntity;
import com.dream.web.query.DictQuery;

/**
 * 
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface DictMapper extends BaseMappers<DictEntity, DictQuery> {

	// List<Dict> list(Page<Dict> page, @Param("query") DictQuery query);
	//
	// List<Dict> list(@Param("query") DictQuery query);
}