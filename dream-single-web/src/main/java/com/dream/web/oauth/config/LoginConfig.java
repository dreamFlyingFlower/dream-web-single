package com.dream.web.oauth.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dream.system.manager.SmsManager;

/**
 * 登录配置信息
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:30:02
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
public class LoginConfig {

	@Bean
	@ConditionalOnMissingBean
	SmsManager smsManager() {
		return new SmsManager() {

			@Override
			public boolean send(String mobile, Map<String, String> params) {
				return false;
			}

			@Override
			public boolean sendCode(String mobile, String key, String value) {
				return false;
			}

			@Override
			public boolean verifyCode(String mobile, String code) {
				return false;
			}
		};
	}
}