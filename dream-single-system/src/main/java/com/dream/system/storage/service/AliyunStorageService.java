package com.dream.system.storage.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dream.framework.properties.StorageProperties;
import com.wy.result.ResultException;

/**
 * 阿里云存储
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:06:22
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class AliyunStorageService extends StorageService {

	public AliyunStorageService(StorageProperties properties) {
		this.properties = properties;
	}

	@Override
	public String upload(byte[] data, String path) {
		return upload(new ByteArrayInputStream(data), path);
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		OSS client = new OSSClientBuilder().build(properties.getAliyun().getEndPoint(),
				properties.getAliyun().getAccessKeyId(), properties.getAliyun().getAccessKeySecret());
		try {
			client.putObject(properties.getAliyun().getBucketName(), path, inputStream);
		} catch (Exception e) {
			throw new ResultException(e, "上传文件失败：");
		} finally {
			if (client != null) {
				client.shutdown();
			}
		}

		return properties.getConfig().getDomain() + "/" + path;
	}

}