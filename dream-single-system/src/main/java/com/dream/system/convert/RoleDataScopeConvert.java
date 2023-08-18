package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.entity.DataScopeEntity;
import com.dream.system.vo.DataScopeVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 角色权限对应关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleDataScopeConvert extends BaseConvert<DataScopeEntity, DataScopeVO> {

	RoleDataScopeConvert INSTANCE = Mappers.getMapper(RoleDataScopeConvert.class);

}