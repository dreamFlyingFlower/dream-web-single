package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.oauth.entity.OAuthClientDetails;
import com.dream.system.oauth.entity.OAuthClientDetailsVO;

import dream.framework.web.convert.BaseConvert;

/**
 * OAuthClient与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OAuthClientDetailsConvert extends BaseConvert<OAuthClientDetails, OAuthClientDetailsVO> {

	OAuthClientDetailsConvert INSTANCE = Mappers.getMapper(OAuthClientDetailsConvert.class);
}