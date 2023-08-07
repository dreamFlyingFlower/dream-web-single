package com.dream.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 加密配置,默认是 BCryptPasswordEncoder()
 *
 * @author 飞花梦影
 * @date 2023-07-08 16:57:58
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
public class PasswordConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}