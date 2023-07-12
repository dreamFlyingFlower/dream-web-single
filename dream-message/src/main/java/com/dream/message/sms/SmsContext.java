package com.dream.message.sms;

import java.util.Map;

import com.dream.message.enums.SmsPlatformEnum;
import com.dream.message.sms.config.SmsConfig;
import com.wy.result.ResultException;

/**
 * 短信 Context
 *
 * @author 飞花梦影
 * @date 2023-07-11 23:59:24
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class SmsContext {

	private final SmsStrategy smsStrategy;

	public SmsContext(SmsConfig config) {
		if (config.getPlatform() == SmsPlatformEnum.ALIYUN.getValue()) {
			this.smsStrategy = new AliyunSmsStrategy(config);
		} else if (config.getPlatform() == SmsPlatformEnum.TENCENT.getValue()) {
			this.smsStrategy = new TencentSmsStrategy(config);
		} else if (config.getPlatform() == SmsPlatformEnum.QINIU.getValue()) {
			this.smsStrategy = new QiniuSmsStrategy(config);
		} else if (config.getPlatform() == SmsPlatformEnum.HUAWEI.getValue()) {
			this.smsStrategy = new HuaweiSmsStrategy(config);
		} else {
			throw new ResultException("未知的短信平台");
		}
	}

	public void send(String mobile, Map<String, String> params) {
		smsStrategy.send(mobile, params);
	}
}