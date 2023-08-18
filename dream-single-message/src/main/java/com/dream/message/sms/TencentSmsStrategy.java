package com.dream.message.sms;

import java.util.Map;

import com.dream.message.sms.config.SmsConfig;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import com.wy.collection.MapTool;
import com.wy.result.ResultException;

import dream.framework.core.constant.ConstCore;

/**
 * 腾讯云短信
 *
 * @author 飞花梦影
 * @date 2023-07-11 23:59:41
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class TencentSmsStrategy implements SmsStrategy {

	private final SmsConfig smsConfig;

	private SmsClient client;

	public TencentSmsStrategy(SmsConfig smsConfig) {
		this.smsConfig = smsConfig;

		try {
			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setReqMethod("POST");
			httpProfile.setEndpoint("sms.tencentcloudapi.com");

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			Credential cred = new Credential(smsConfig.getAccessKey(), smsConfig.getSecretKey());
			this.client = new SmsClient(cred, "ap-guangzhou", clientProfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(String mobile, Map<String, String> params) {
		SendSmsRequest request = new SendSmsRequest();
		request.setSmsSdkAppId(smsConfig.getAppId());
		request.setSignName(smsConfig.getSignName());
		request.setTemplateId(smsConfig.getTemplateId());

		// 有参数则设置
		if (MapTool.isNotEmpty(params)) {
			request.setTemplateParamSet(params.values().toArray(new String[0]));
		}

		// 手机号
		String[] phoneNumberSet = { "+86" + mobile };
		request.setPhoneNumberSet(phoneNumberSet);

		// 国际、港澳台短信,需要添加SenderId,国内短信填空,默认未开通
		request.setSenderId(smsConfig.getSenderId());

		try {
			// 发送短信
			SendSmsResponse response = client.SendSms(request);
			SendStatus sendStatus = response.getSendStatusSet()[0];

			// 发送失败
			if (!ConstCore.OK.equalsIgnoreCase(sendStatus.getCode())) {
				throw new ResultException(sendStatus.getMessage());
			}
		} catch (Exception e) {
			throw new ResultException(e.getMessage());
		}
	}
}