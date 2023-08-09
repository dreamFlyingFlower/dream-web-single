package com.dream.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.web.entity.RoleResourceEntity;
import com.dream.web.vo.RoleResourceVO;

/**
 * 角色-权限关系数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleResourceConvert extends BaseConvert<RoleResourceEntity, RoleResourceVO> {

	RoleResourceConvert INSTANCE = Mappers.getMapper(RoleResourceConvert.class);

}