package com.dream.message.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.message.entity.SmsLogEntity;
import com.dream.message.vo.SmsLogVO;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:17:29
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface SmsLogConvert extends BaseConvert<SmsLogEntity, SmsLogVO> {

	SmsLogConvert INSTANCE = Mappers.getMapper(SmsLogConvert.class);

}