package com.dream.message.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.sms.config.SmsConfig;
import com.dream.message.vo.SmsPlatformVO;

import dream.framework.web.convert.BaseConvert;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:23:38
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SmsPlatformConvert extends BaseConvert<SmsPlatformEntity, SmsPlatformVO> {

	SmsPlatformConvert INSTANCE = Mappers.getMapper(SmsPlatformConvert.class);

	SmsConfig convert2(SmsPlatformEntity entity);

	List<SmsConfig> convertList2(List<SmsPlatformEntity> list);
}