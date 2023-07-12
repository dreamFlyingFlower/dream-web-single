package com.dream.framework.web.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典详情DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "字典详情DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictItemDTO {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 字典编码
	 */
	@ApiModelProperty("字典编码")
	private String dictCode;

	/**
	 * 字典值
	 */
	@ApiModelProperty("字典值")
	private String dictValue;

	/**
	 * 字典名
	 */
	@ApiModelProperty("字典名")
	private String dictName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@ApiModelProperty("创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * 更新人ID
	 */
	@ApiModelProperty("更新人ID")
	private Long updateUser;
}