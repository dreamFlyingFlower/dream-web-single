package com.dream.web.storage.service;

import java.io.IOException;
import java.io.InputStream;

import com.dream.web.properties.StorageProperties;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import com.wy.result.ResultException;

/**
 * 七牛云存储
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:13:47
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class QiniuStorageService extends StorageService {

	private final UploadManager uploadManager;

	private final String token;

	public QiniuStorageService(StorageProperties properties) {
		this.properties = properties;

		uploadManager = new UploadManager(new Configuration(Region.autoRegion()));
		token = Auth.create(properties.getQiniu().getAccessKey(), properties.getQiniu().getSecretKey())
				.uploadToken(properties.getQiniu().getBucketName());
	}

	@Override
	public String upload(byte[] data, String path) {
		try {
			Response res = uploadManager.put(data, path, token);
			if (!res.isOK()) {
				throw new ResultException(res.toString());
			}

			return properties.getConfig().getDomain() + "/" + path;
		} catch (Exception e) {
			throw new ResultException(e, "上传文件失败：");
		}
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			return this.upload(data, path);
		} catch (IOException e) {
			throw new ResultException(e, "上传文件失败：");
		}
	}
}