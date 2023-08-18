package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.entity.LogLoginEntity;
import com.dream.system.vo.LogLoginVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 登录日志Entity<->VO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LogLoginConvert extends BaseConvert<LogLoginEntity, LogLoginVO> {

	LogLoginConvert INSTANCE = Mappers.getMapper(LogLoginConvert.class);

}