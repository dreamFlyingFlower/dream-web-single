package com.dream.system.storage.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.util.FileCopyUtils;

import com.dream.framework.properties.StorageProperties;
import com.wy.result.ResultException;

/**
 * 本地存储
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:09:31
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class LocalStorageService extends StorageService {

	public LocalStorageService(StorageProperties properties) {
		this.properties = properties;
	}

	@Override
	public String upload(byte[] data, String path) {
		return upload(new ByteArrayInputStream(data), path);
	}

	@Override
	public String upload(InputStream inputStream, String path) {

		try {
			File file = new File(properties.getLocal().getPath() + File.separator + path);

			// 没有目录,则自动创建目录
			File parent = file.getParentFile();
			if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
				throw new IOException("目录 '" + parent + "' 创建失败");
			}

			FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
		} catch (Exception e) {
			throw new ResultException(e, "上传文件失败：");
		}

		return properties.getConfig().getDomain() + "/" + properties.getLocal().getUrl() + "/" + path;
	}
}