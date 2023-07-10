package com.dream.message.api;

import lombok.AllArgsConstructor;

import com.dream.message.cache.SmsSendCache;
import com.dream.message.sms.service.SmsService;
import com.electric.api.module.message.SmsApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务Api
 *
 * @author 飞花梦影
 * @date 2023-07-10 23:55:44
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Component
@AllArgsConstructor
public class SmsApiImpl implements SmsApi {

	@Autowired
	private SmsService smsService;

	@Autowired
	private SmsSendCache smsSendCache;

	@Override
	public boolean send(String mobile, Map<String, String> params) {
		return smsService.send(mobile, params);
	}

	@Override
	public boolean sendCode(String mobile, String key, String value) {
		// 短信参数
		Map<String, String> params = new HashMap<>();
		params.put(key, value);

		// 发送短信
		boolean flag = smsService.send(mobile, params);
		if (flag) {
			smsSendCache.saveCode(mobile, value);
		}
		return flag;
	}

	@Override
	public boolean verifyCode(String mobile, String code) {
		String value = smsSendCache.getCode(mobile);
		if (value != null) {
			// 删除短信验证码
			smsSendCache.deleteCode(mobile);

			// 效验
			return value.equalsIgnoreCase(code);
		}
		return false;
	}
}