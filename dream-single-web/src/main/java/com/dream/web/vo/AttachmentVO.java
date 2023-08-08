package com.dream.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wy.ConstDate;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 附件管理 VO
 * 
 * @author 飞花梦影
 * @date 2022-08-31 14:54:31
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Schema(description = "附件管理")
@ApiModel(value = "AttachmentVO", description = "附件管理 VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "附件名称")
	private String name;

	@Schema(description = "附件地址")
	private String url;

	@Schema(description = "附件大小")
	private Long size;

	@Schema(description = "存储平台")
	private String platform;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date createTime;
}