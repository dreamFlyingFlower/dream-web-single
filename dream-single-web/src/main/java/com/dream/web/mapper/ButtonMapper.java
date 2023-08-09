package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.ButtonEntity;
import com.dream.web.query.ButtonQuery;
import com.dream.web.vo.ButtonVO;

/**
 * 按钮表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface ButtonMapper extends BaseMappers<ButtonEntity, ButtonVO, ButtonQuery> {

}