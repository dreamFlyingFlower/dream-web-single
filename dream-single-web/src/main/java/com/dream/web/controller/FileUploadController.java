package com.dream.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dream.basic.web.controller.BaseController;
import com.dream.web.storage.service.StorageService;
import com.dream.web.vo.FileUploadVO;
import com.wy.result.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 文件上传
 *
 * @author 飞花梦影
 * @date 2023-08-09 15:40:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/file")
@Tag(name = "文件上传")
@AllArgsConstructor
public class FileUploadController implements BaseController {

	private final StorageService storageService;

	@Value("${storage.minio.prefix}")
	private String prefix;

	@PostMapping("upload")
	@Operation(summary = "上传")
	public Result<FileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return Result.error("请选择需要上传的文件");
		}

		// 上传路径
		String path = storageService.getPath(file.getOriginalFilename());
		// 上传文件
		String url = storageService.upload(file.getBytes(), prefix + "/" + path);

		FileUploadVO vo = new FileUploadVO();
		vo.setUrl(url);
		vo.setSize(file.getSize());
		vo.setName(file.getOriginalFilename());
		vo.setPlatform(storageService.properties.getConfig().getType().name());
		return Result.ok(vo);
	}
}