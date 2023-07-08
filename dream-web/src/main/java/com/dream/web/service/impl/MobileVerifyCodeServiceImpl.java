package com.dream.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.web.mobile.MobileVerifyCodeService;

/**
 * 短信验证码效验
 *
 * @author 飞花梦影
 * @date 2023-07-08 17:20:46
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {

	@Autowired
	private SmsApi smsApi;

	@Override
	public boolean verifyCode(String mobile, String code) {
		return smsApi.verifyCode(mobile, code);
	}
}