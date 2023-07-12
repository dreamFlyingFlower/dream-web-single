package com.dream.message.sms.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dream.framework.constant.ConstCommon;
import com.dream.message.cache.SmsPlatformCache;
import com.dream.message.entity.SmsLogEntity;
import com.dream.message.service.SmsLogService;
import com.dream.message.service.SmsPlatformService;
import com.dream.message.sms.SmsContext;
import com.dream.message.sms.config.SmsConfig;
import com.wy.collection.MapTool;
import com.wy.result.ResultException;
import com.wy.third.json.JsonTools;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信服务
 *
 * @author 飞花梦影
 * @date 2023-07-12 10:01:23
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Slf4j
@Service
@AllArgsConstructor
public class SmsService {

	private final SmsPlatformService smsPlatformService;

	private final SmsLogService smsLogService;

	private final SmsPlatformCache smsCacheService;

	/**
	 * 发送短信
	 * 
	 * @param mobile 手机号
	 * @return 是否发送成功
	 */
	public boolean send(String mobile) {
		return this.send(mobile, MapTool.newHashMap());
	}

	/**
	 * 发送短信
	 * 
	 * @param mobile 手机号
	 * @param params 参数
	 * @return 是否发送成功
	 */
	public boolean send(String mobile, Map<String, String> params) {
		SmsConfig config = roundSmsConfig();
		try {
			// 发送短信
			new SmsContext(config).send(mobile, params);
			saveLog(config, mobile, params, null);
			return true;
		} catch (Exception e) {
			log.error("短信发送失败，手机号：{}", mobile, e);
			saveLog(config, mobile, params, e);
			return false;
		}
	}

	/**
	 * 保存短信日志
	 */
	public void saveLog(SmsConfig config, String mobile, Map<String, String> params, Exception e) {
		SmsLogEntity logEntity = new SmsLogEntity();
		logEntity.setPlatform(config.getPlatform());
		logEntity.setPlatformId(config.getId());
		logEntity.setMobile(mobile);
		logEntity.setParams(JsonTools.toJson(params));

		if (e != null) {
			String error = StringUtils.substring(e.getMessage(), 0, 2000);
			logEntity.setStatus(ConstCommon.FAIL);
			logEntity.setError(error);
		} else {
			logEntity.setStatus(ConstCommon.SUCCESS);
		}

		smsLogService.save(logEntity);
	}

	/**
	 * 通过轮询算法，获取短信平台的配置
	 */
	private SmsConfig roundSmsConfig() {
		List<SmsConfig> platformList = smsPlatformService.listByEnable();

		// 是否有可用的短信平台
		int count = platformList.size();
		if (count == 0) {
			throw new ResultException("没有可用的短信平台，请先添加");
		}

		// 采用轮询算法，发送短信
		long round = smsCacheService.getRoundValue();

		return platformList.get((int) round % count);
	}
}