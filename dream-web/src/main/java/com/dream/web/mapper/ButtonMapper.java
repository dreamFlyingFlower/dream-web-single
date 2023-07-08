package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.Button;
import com.dream.web.query.ButtonQuery;

/**
 * 按钮表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface ButtonMapper extends BaseMappers<Button,ButtonQuery> {

	// List<Button> list(Page<Button> page, @Param("query") ButtonQuery query);
	//
	// List<Button> list(@Param("query") ButtonQuery query);
}