package com.dream.web.storage.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;

import com.dream.web.properties.StorageProperties;
import com.wy.lang.StrTool;
import com.wy.result.ResultException;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

/**
 * Minio存储
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:13:02
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class MinioStorageService extends StorageService {

	private final MinioClient minioClient;

	public MinioStorageService(StorageProperties properties) {
		this.properties = properties;

		minioClient = MinioClient.builder().endpoint(properties.getMinio().getEndPoint())
				.credentials(properties.getMinio().getAccessKey(), properties.getMinio().getSecretKey()).build();
	}

	@Override
	public String upload(byte[] data, String path) {
		return upload(new ByteArrayInputStream(data), path);
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		try {
			// 如果BucketName不存在,则创建
			boolean found = minioClient
					.bucketExists(BucketExistsArgs.builder().bucket(properties.getMinio().getBucketName()).build());
			if (!found) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getMinio().getBucketName()).build());
			}
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String type = fileNameMap.getContentTypeFor(path);
			PutObjectArgs args;
			if (StrTool.isNotBlank(type)) {
				args = PutObjectArgs.builder().bucket(properties.getMinio().getBucketName()).object(path)
						.stream(inputStream, inputStream.available(), -1).contentType(type).build();
			} else {
				args = PutObjectArgs.builder().bucket(properties.getMinio().getBucketName()).object(path)
						.stream(inputStream, inputStream.available(), -1).build();
			}
			minioClient.putObject(args);
		} catch (Exception e) {
			throw new ResultException(e, "上传文件失败：");
		}

		return properties.getMinio().getEndPoint() + "/" + properties.getMinio().getBucketName() + "/" + path;
	}
}