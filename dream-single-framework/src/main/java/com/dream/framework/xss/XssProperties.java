package com.dream.framework.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * XSS 配置项
 *
 * @author 飞花梦影
 * @date 2023-08-10 15:04:34
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Data
@ConfigurationProperties(prefix = "dream.xss")
public class XssProperties {

	/**
	 * 是否开启 XSS
	 */
	private boolean enabled;

	/**
	 * 排除的URL列表
	 */
	private List<String> excludeUrls = Collections.emptyList();
}