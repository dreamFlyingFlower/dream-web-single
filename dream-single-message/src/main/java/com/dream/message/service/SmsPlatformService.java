package com.dream.message.service;

import java.util.List;

import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.query.SmsPlatformQuery;
import com.dream.message.sms.config.SmsConfig;
import com.dream.message.vo.SmsPlatformVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:16:01
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface SmsPlatformService extends BaseServices<SmsPlatformEntity, SmsPlatformVO, SmsPlatformQuery> {

	List<SmsConfig> listByEnable();

	void save(SmsPlatformVO vo);

	void update(SmsPlatformVO vo);

	void delete(List<Long> idList);
}