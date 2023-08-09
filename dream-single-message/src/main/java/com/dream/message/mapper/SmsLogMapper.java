package com.dream.message.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.message.entity.SmsLogEntity;
import com.dream.message.query.SmsLogQuery;
import com.dream.message.vo.SmsLogVO;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:00:54
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface SmsLogMapper extends BaseMappers<SmsLogEntity, SmsLogVO, SmsLogQuery> {

}