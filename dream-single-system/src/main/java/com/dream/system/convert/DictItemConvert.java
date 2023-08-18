package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.entity.DictItemEntity;
import com.dream.system.vo.DictItemVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 字典项数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DictItemConvert extends BaseConvert<DictItemEntity, DictItemVO> {

	DictItemConvert INSTANCE = Mappers.getMapper(DictItemConvert.class);

}