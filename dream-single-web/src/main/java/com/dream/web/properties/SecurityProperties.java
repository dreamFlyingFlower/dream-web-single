package com.dream.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * SpringSecurity相关配置
 *
 * @author 飞花梦影
 * @date 2023-08-04 17:09:28
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
@ConfigurationProperties("dream.security")
@Getter
@Setter
public class SecurityProperties {

	private String[] httpIgnoreResources;

	private String[] webIgnoreResources;
}