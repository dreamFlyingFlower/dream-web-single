package com.dream.system.manager;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.dream.framework.manager.StorageManager;
import com.dream.system.storage.service.StorageService;

import lombok.AllArgsConstructor;

/**
 * 存储服务
 *
 * @author 飞花梦影
 * @date 2023-08-08 13:27:10
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Component
@AllArgsConstructor
public class StorageManagerImpl implements StorageManager {

	private final StorageService storageService;

	@Override
	public String getNewFileName(String fileName) {
		return storageService.getNewFileName(fileName);
	}

	@Override
	public String getPath() {
		return storageService.getPath();
	}

	@Override
	public String getPath(String fileName) {
		return storageService.getPath(fileName);
	}

	@Override
	public String upload(byte[] data, String path) {
		return storageService.upload(data, path);
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		return storageService.upload(inputStream, path);
	}
}