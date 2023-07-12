package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.framework.web.query.DictItemQuery;
import com.dream.web.entity.DictItem;

/**
 * 
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface DictItemMapper extends BaseMappers<DictItem, DictItemQuery> {

	// List<DictItem> list(Page<DictItem> page, @Param("query") DictItemQuery
	// query);
	//
	// List<DictItem> list(@Param("query") DictItemQuery query);
}