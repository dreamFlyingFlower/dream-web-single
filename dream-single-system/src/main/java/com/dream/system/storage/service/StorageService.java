package com.dream.system.storage.service;

import java.io.InputStream;

import org.springframework.util.StringUtils;

import com.dream.framework.properties.StorageProperties;
import com.wy.enums.DateEnum;
import com.wy.io.file.FileNameTool;
import com.wy.util.DateTool;

/**
 * 存储服务
 *
 * @author 飞花梦影
 * @date 2023-08-08 11:14:36
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public abstract class StorageService {

	public StorageProperties properties;

	/**
	 * 根据文件名,生成带时间戳的新文件名
	 *
	 * @param fileName 文件名
	 * @return 返回带时间戳的文件名
	 */
	public String getNewFileName(String fileName) {
		return FileNameTool.getBaseName(fileName) + "_" + System.currentTimeMillis() + "."
				+ FileNameTool.getExtension(fileName);
	}

	/**
	 * 生成路径,不包含文件名
	 *
	 * @return 返回生成的路径
	 */
	public String getPath() {
		String path = DateTool.format(DateEnum.DATE_NONE);
		// 如果有前缀,则也带上
		if (StringUtils.hasText(properties.getConfig().getPrefix())) {
			path = properties.getConfig().getPrefix() + "/" + path;
		}
		return path;
	}

	/**
	 * 根据文件名,生成路径
	 *
	 * @param fileName 文件名
	 * @return 生成文件路径
	 */
	public String getPath(String fileName) {
		return getPath() + "/" + getNewFileName(fileName);
	}

	/**
	 * 文件上传
	 *
	 * @param data 文件字节数组
	 * @param path 文件路径,包含文件名
	 * @return 返回http地址
	 */
	public abstract String upload(byte[] data, String path);

	/**
	 * 文件上传
	 *
	 * @param inputStream 字节流
	 * @param path 文件路径,包含文件名
	 * @return 返回http地址
	 */
	public abstract String upload(InputStream inputStream, String path);
}