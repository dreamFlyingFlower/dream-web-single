package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.entity.DictEntity;
import com.dream.system.query.DictQuery;
import com.dream.system.vo.DictVO;

import dream.framework.web.mapper.BaseMappers;

/**
 * 字典
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface DictMapper extends BaseMappers<DictEntity, DictVO, DictQuery> {

}