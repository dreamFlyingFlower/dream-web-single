package com.dream.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.electric.framework.utils.Result;
import com.electric.storage.service.StorageService;
import com.electric.system.vo.SysFileUploadVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author  
 */
@RestController
@RequestMapping("sys/file")
@Tag(name = "文件上传")
public class SysFileUploadController {
	
	@Autowired
    private  StorageService storageService;
    
    @Value("${storage.minio.prefix}")
    private  String prefix;
    
    @PostMapping("upload")
    @Operation(summary = "上传")
    public Result<SysFileUploadVO> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }

        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传文件
        String url = storageService.upload(file.getBytes(), prefix+"/"+path);

        SysFileUploadVO vo = new SysFileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());
        vo.setPlatform(storageService.properties.getConfig().getType().name());

        return Result.ok(vo);
    }
    
 
	
    
	
}