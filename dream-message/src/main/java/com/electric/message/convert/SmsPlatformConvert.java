package com.electric.message.convert;

import com.electric.message.entity.SmsPlatformEntity;
import com.electric.message.sms.config.SmsConfig;
import com.electric.message.vo.SmsPlatformVO;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 短信平台
*
* @author  
*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SmsPlatformConvert {
    SmsPlatformConvert INSTANCE = Mappers.getMapper(SmsPlatformConvert.class);

    SmsPlatformEntity convert(SmsPlatformVO vo);

    SmsPlatformVO convert(SmsPlatformEntity entity);

    List<SmsPlatformVO> convertList(List<SmsPlatformEntity> list);

    SmsConfig convert2(SmsPlatformEntity entity);

    List<SmsConfig> convertList2(List<SmsPlatformEntity> list);

}