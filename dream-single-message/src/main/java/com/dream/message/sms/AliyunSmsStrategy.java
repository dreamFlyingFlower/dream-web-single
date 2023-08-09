package com.dream.message.sms;

import java.util.Map;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.dream.basic.core.constant.ConstCore;
import com.dream.message.sms.config.SmsConfig;
import com.wy.collection.MapTool;
import com.wy.result.ResultException;
import com.wy.third.json.JsonTools;

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
		if (MapTool.isNotEmpty(params)) {
			request.setTemplateParam(JsonTools.toJson(params));
		}

		try {
			// 发送短信
			SendSmsResponse response = client.sendSms(request);

			// 发送失败
			if (!ConstCore.OK.equalsIgnoreCase(response.getBody().getCode())) {
				throw new ResultException(response.getBody().getMessage());
			}
		} catch (Exception e) {
			throw new ResultException(e.getMessage());
		}
	}
}