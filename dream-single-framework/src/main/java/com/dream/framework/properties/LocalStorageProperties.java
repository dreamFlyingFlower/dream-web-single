package com.dream.framework.properties;

import lombok.Data;

/**
 * 本地存储配置项
 *
 * @author 飞花梦影
 * @date 2023-08-10 14:19:08
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Data
public class LocalStorageProperties {

	/**
	 * 本地存储路径
	 */
	private String path;

	/**
	 * 资源起始路径
	 */
	private String url = "upload";
}