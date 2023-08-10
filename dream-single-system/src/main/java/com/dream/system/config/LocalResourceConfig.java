package com.dream.system.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dream.framework.enums.StorageType;
import com.dream.framework.properties.LocalStorageProperties;
import com.dream.framework.properties.StorageProperties;

/**
 * 本地资源映射配置
 *
 * @author 飞花梦影
 * @date 2023-08-08 13:30:34
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
@ConditionalOnProperty(prefix = "storage", value = "enabled")
public class LocalResourceConfig implements WebMvcConfigurer {

	@Resource
	private StorageProperties properties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 如果不是本地存储，则返回
		if (properties.getConfig().getType() != StorageType.LOCAL) {
			return;
		}

		LocalStorageProperties local = properties.getLocal();
		registry.addResourceHandler("/" + local.getUrl() + "/**").addResourceLocations("file:" + local.getPath() + "/");
	}
}