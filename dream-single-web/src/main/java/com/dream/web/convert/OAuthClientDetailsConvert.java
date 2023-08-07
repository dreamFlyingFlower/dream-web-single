package com.dream.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.web.entity.OAuthClientDetails;
import com.dream.web.entity.OAuthClientDetailsDTO;

/**
 * OAuthClient与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OAuthClientDetailsConvert extends BaseConvert<OAuthClientDetails, OAuthClientDetailsDTO> {

	OAuthClientDetailsConvert INSTANCE = Mappers.getMapper(OAuthClientDetailsConvert.class);
}