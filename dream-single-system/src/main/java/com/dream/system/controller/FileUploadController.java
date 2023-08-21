package com.dream.system.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dream.system.vo.FileUploadVO;
import com.wy.result.Result;

import dream.framework.storage.StorageManager;
import dream.framework.storage.properties.StorageProperties;
import dream.framework.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 文件上传API
 *
 * @author 飞花梦影
 * @date 2023-08-09 15:40:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/file")
@Tag(name = "文件上传API")
@AllArgsConstructor
public class FileUploadController implements BaseController {

	private final StorageManager storageManager;

	private final StorageProperties storageProperties;

	@PostMapping("upload")
	@Operation(summary = "上传")
	public Result<FileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return Result.error("请选择需要上传的文件");
		}

		// 上传路径
		String path = storageManager.getPath(file.getOriginalFilename());
		// 上传文件
		String url = storageManager.upload(file.getBytes(), storageProperties.getPrefix() + File.separator + path);

		FileUploadVO vo = new FileUploadVO();
		vo.setUrl(url);
		vo.setSize(file.getSize());
		vo.setName(file.getOriginalFilename());
		vo.setPlatform(storageProperties.getType().name());
		return Result.ok(vo);
	}
}