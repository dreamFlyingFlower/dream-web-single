package com.dream.web.storage.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.dream.web.properties.StorageProperties;
import com.obs.services.ObsClient;
import com.wy.result.ResultException;

/**
 * 华为云存储
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:09:13
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class HuaweiStorageService extends StorageService {

	public HuaweiStorageService(StorageProperties properties) {
		this.properties = properties;
	}

	@Override
	public String upload(byte[] data, String path) {
		return upload(new ByteArrayInputStream(data), path);
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		ObsClient client = new ObsClient(properties.getHuawei().getAccessKey(), properties.getHuawei().getSecretKey(),
				properties.getHuawei().getEndPoint());
		try {
			client.putObject(properties.getHuawei().getBucketName(), path, inputStream);
			client.close();
		} catch (Exception e) {
			throw new ResultException(e, "上传文件失败：");
		}

		return properties.getConfig().getDomain() + "/" + path;
	}

}