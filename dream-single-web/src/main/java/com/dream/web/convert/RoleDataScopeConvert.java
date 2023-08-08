package com.dream.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.web.entity.RoleDataScopeEntity;
import com.dream.web.vo.RoleDataScopeVO;

/**
 * 角色权限对应关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleDataScopeConvert extends BaseConvert<RoleDataScopeEntity, RoleDataScopeVO> {

	RoleDataScopeConvert INSTANCE = Mappers.getMapper(RoleDataScopeConvert.class);

}