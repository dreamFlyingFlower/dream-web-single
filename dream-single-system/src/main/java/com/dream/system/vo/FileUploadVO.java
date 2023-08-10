package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传
 * 
 * @author 飞花梦影
 * @date 2023-08-08 09:52:30
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "文件上传")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "文件名称")
	private String name;

	@Schema(description = "文件地址")
	private String url;

	@Schema(description = "文件大小")
	private Long size;

	@Schema(description = "存储平台")
	private String platform;
}