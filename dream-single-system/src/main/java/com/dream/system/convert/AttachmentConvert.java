package com.dream.system.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.system.entity.AttachmentEntity;
import com.dream.system.vo.AttachmentVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 按钮表数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttachmentConvert extends BaseConvert<AttachmentEntity, AttachmentVO> {

	AttachmentConvert INSTANCE = Mappers.getMapper(AttachmentConvert.class);

}