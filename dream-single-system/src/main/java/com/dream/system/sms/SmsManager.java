package com.dream.system.sms;

import java.util.Map;

/**
 * 
 *
 * @author 飞花梦影
 * @date 2023-07-10 16:30:07
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface SmsManager {

	/**
	 * 发送短信
	 *
	 * @param mobile 手机号
	 * @param params 参数
	 * @return true->成功;false->失败
	 */
	boolean send(String mobile, Map<String, String> params);

	/**
	 * 发送短信
	 *
	 * @param mobile 手机号
	 * @param key 参数KEY
	 * @param value 参数Value
	 * @return true->成功;false->失败
	 */
	boolean sendCode(String mobile, String key, String value);

	/**
	 * 验证手机号和验证码
	 * 
	 * @param mobile 手机号
	 * @param code 验证码
	 * @return true->成功;false->失败
	 */
	boolean verifyCode(String mobile, String code);
}