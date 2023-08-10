package com.dream.framework.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 存储类型枚举
 *
 * @author 飞花梦影
 * @date 2023-08-10 11:27:15
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StorageType {
	/**
	 * 本地
	 */
	LOCAL,
	/**
	 * 阿里云
	 */
	ALIYUN,
	/**
	 * 腾讯云
	 */
	TENCENT,
	/**
	 * 七牛云
	 */
	QINIU,
	/**
	 * 华为云
	 */
	HUAWEI,
	/**
	 * Minio
	 */
	MINIO;
}