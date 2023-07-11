package com.dream.message.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.query.SmsPlatformQuery;
import com.dream.message.sms.config.SmsConfig;
import com.dream.message.vo.SmsPlatformVO;
import com.wy.result.Result;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:16:01
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface SmsPlatformService extends BaseService<SmsPlatformEntity, SmsPlatformVO, SmsPlatformQuery> {

	Result<List<SmsPlatformVO>> page(SmsPlatformQuery query);

	List<SmsConfig> listByEnable();

	void save(SmsPlatformVO vo);

	void update(SmsPlatformVO vo);

	void delete(List<Long> idList);
}