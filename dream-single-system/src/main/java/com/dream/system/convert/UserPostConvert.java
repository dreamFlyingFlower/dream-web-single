package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.entity.UserPostEntity;
import com.dream.system.vo.UserPostVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 用户岗位表数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserPostConvert extends BaseConvert<UserPostEntity, UserPostVO> {

	UserPostConvert INSTANCE = Mappers.getMapper(UserPostConvert.class);

}