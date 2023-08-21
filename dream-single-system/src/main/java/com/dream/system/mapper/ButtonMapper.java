package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.entity.ButtonEntity;
import com.dream.system.query.ButtonQuery;
import com.dream.system.vo.ButtonVO;

import dream.framework.mybatis.plus.mapper.BaseMappers;

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