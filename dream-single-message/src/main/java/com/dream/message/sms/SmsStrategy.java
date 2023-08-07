package com.dream.message.sms;

import java.util.Map;

/**
 * 短信
 *
 * @author 飞花梦影
 * @date 2023-07-11 23:59:33
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface SmsStrategy {

	/**
	 * 发送短信
	 * 
	 * @param mobile 手机号
	 * @param params 参数
	 */
	void send(String mobile, Map<String, String> params);
}