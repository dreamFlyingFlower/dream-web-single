package com.dream.framework.properties;

import lombok.Data;

/**
 * 七牛云存储配置项
 *
 * @author 飞花梦影
 * @date 2023-08-10 14:19:20
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Data
public class QiniuStorageProperties {

	private String accessKey;

	private String secretKey;

	private String bucketName;
}