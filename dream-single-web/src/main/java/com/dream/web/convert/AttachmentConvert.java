package com.dream.web.convert;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.system.web.vo.AttachmentVO;
import com.dream.web.entity.AttachmentEntity;

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