package com.dream.system.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dream.framework.enums.StorageType;
import com.dream.framework.properties.StorageProperties;
import com.dream.system.storage.service.AliyunStorageService;
import com.dream.system.storage.service.HuaweiStorageService;
import com.dream.system.storage.service.LocalStorageService;
import com.dream.system.storage.service.MinioStorageService;
import com.dream.system.storage.service.QiniuStorageService;
import com.dream.system.storage.service.StorageService;
import com.dream.system.storage.service.TencentStorageService;

/**
 * 存储配置文件
 * 
 * @author 飞花梦影
 * @date 2023-08-08 13:31:07
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@ConditionalOnProperty(prefix = "storage", value = "enabled")
public class StorageConfig {

	@Bean
	public StorageService storageService(StorageProperties properties) {
		if (properties.getConfig().getType() == StorageType.LOCAL) {
			return new LocalStorageService(properties);
		} else if (properties.getConfig().getType() == StorageType.ALIYUN) {
			return new AliyunStorageService(properties);
		} else if (properties.getConfig().getType() == StorageType.TENCENT) {
			return new TencentStorageService(properties);
		} else if (properties.getConfig().getType() == StorageType.QINIU) {
			return new QiniuStorageService(properties);
		} else if (properties.getConfig().getType() == StorageType.HUAWEI) {
			return new HuaweiStorageService(properties);
		} else if (properties.getConfig().getType() == StorageType.MINIO) {
			return new MinioStorageService(properties);
		}
		return null;
	}
}