package com.dream.web.service;

import com.dream.web.vo.CaptchaVO;

/**
 * 验证码
 *
 * @author 飞花梦影
 * @date 2023-08-08 13:35:25
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface CaptchaService {

	/**
	 * 生成验证码
	 */
	CaptchaVO generate();

	/**
	 * 验证码效验
	 *
	 * @param key key
	 * @param code 验证码
	 * @return true：成功 false：失败
	 */
	boolean validate(String key, String code);
}