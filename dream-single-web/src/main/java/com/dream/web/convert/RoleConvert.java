package com.dream.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.web.entity.RoleEntity;
import com.dream.web.vo.RoleVO;

/**
 * 角色信息数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleConvert extends BaseConvert<RoleEntity, RoleVO> {

	RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

}