package com.dream.message.service;

import com.dream.message.entity.SmsLogEntity;
import com.dream.message.query.SmsLogQuery;
import com.dream.message.vo.SmsLogVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:15:31
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface SmsLogService extends BaseServices<SmsLogEntity, SmsLogVO, SmsLogQuery> {

}