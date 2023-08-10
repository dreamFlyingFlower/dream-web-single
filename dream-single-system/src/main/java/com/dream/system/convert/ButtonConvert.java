package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.system.entity.ButtonEntity;
import com.dream.system.vo.ButtonVO;

/**
 * 按钮表数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ButtonConvert extends BaseConvert<ButtonEntity, ButtonVO> {

	ButtonConvert INSTANCE = Mappers.getMapper(ButtonConvert.class);

}