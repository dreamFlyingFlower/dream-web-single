package com.dream.framework.properties;

import lombok.Data;

/**
 * 阿里云存储配置项
 *
 * @author 飞花梦影
 * @date 2023-08-10 14:18:54
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Data
public class AliyunStorageProperties {

	private String endPoint;

	private String accessKeyId;

	private String accessKeySecret;

	private String bucketName;
}