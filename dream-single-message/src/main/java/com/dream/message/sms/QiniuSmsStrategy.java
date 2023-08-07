package com.dream.message.sms;

import java.util.Map;

import com.dream.message.sms.config.SmsConfig;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import com.wy.result.ResultException;

/**
 * 七牛云短信
 *
 * @author 飞花梦影
 * @date 2023-07-11 23:59:11
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class QiniuSmsStrategy implements SmsStrategy {

	private final SmsConfig smsConfig;

	private final SmsManager smsManager;

	public QiniuSmsStrategy(SmsConfig smsConfig) {
		this.smsConfig = smsConfig;

		Auth auth = Auth.create(smsConfig.getAccessKey(), smsConfig.getSecretKey());
		smsManager = new SmsManager(auth);
	}

	@Override
	public void send(String mobile, Map<String, String> params) {
		try {
			Response response = smsManager.sendSingleMessage(smsConfig.getTemplateId(), mobile, params);

			// 是否发送成功
			if (!response.isOK()) {
				throw new ResultException(response.error);
			}
		} catch (Exception e) {
			throw new ResultException(e.getMessage());
		}
	}
}