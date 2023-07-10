package com.electric.message.sms;

import java.util.Map;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.electric.framework.constant.Constant;
import com.electric.framework.exception.ServerException;
import com.electric.framework.utils.JsonUtils;
import com.electric.message.sms.config.SmsConfig;

import cn.hutool.core.map.MapUtil;

/**
 * 阿里云短信
 *
 * @author
 */
public class AliyunSmsStrategy implements SmsStrategy {

	private final Client client;

	private final SmsConfig smsConfig;

	public AliyunSmsStrategy(SmsConfig smsConfig) {
		this.smsConfig = smsConfig;

		try {
			Config config = new Config();
			config.setAccessKeyId(smsConfig.getAccessKey());
			config.setAccessKeySecret(smsConfig.getSecretKey());
			config.endpoint = "dysmsapi.aliyuncs.com";

			this.client = new Client(config);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void send(String mobile, Map<String, String> params) {
		SendSmsRequest request = new SendSmsRequest();
		request.setSignName(smsConfig.getSignName());
		request.setTemplateCode(smsConfig.getTemplateId());
		request.setPhoneNumbers(mobile);
		// request.setTemplateParam("{\"code\":\"1234\"}");
		if (MapUtil.isNotEmpty(params)) {
			request.setTemplateParam(JsonUtils.toJsonString(params));
		}

		try {
			// 发送短信
			SendSmsResponse response = client.sendSms(request);

			// 发送失败
			if (!Constant.OK.equalsIgnoreCase(response.getBody().getCode())) {
				throw new ServerException(response.getBody().getMessage());
			}
		} catch (Exception e) {
			throw new ServerException(e.getMessage());
		}
	}
}