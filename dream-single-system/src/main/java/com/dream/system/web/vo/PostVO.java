package com.dream.system.web.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wy.ConstDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 岗位管理VO
 *
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "岗位管理VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "岗位编码", required = true)
	@NotBlank(message = "岗位编码不能为空")
	private String postCode;

	@Schema(description = "岗位名称", required = true)
	@NotBlank(message = "岗位名称不能为空")
	private String postName;

	@Schema(description = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;

	@Schema(description = "状态  0：停用   1：正常", required = true)
	@Range(min = 0, max = 1, message = "状态不正确")
	private Integer status;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date createTime;
}