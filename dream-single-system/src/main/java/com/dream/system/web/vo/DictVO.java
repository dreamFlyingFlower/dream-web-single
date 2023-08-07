package com.dream.system.web.vo;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wy.ConstDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "字典DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictVO {

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
	@NotBlank(message = "字典编码不能为空")
	private String dictCode;

	/**
	 * 字典名称
	 */
	@ApiModelProperty("字典名称")
	@NotBlank(message = "字典名称不能为空")
	private String dictName;

	/**
	 * 来源 0：字典数据 1：动态SQL
	 */
	@Schema(description = "来源  0：字典数据  1：动态SQL")
	private Integer dictSource;

	/**
	 * 动态sql
	 */
	@Schema(description = "动态sql")
	private String dictSql;

	/**
	 * 排序
	 */
	@Schema(description = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;

	/**
	 * 备注
	 */
	@Schema(description = "备注")
	private String remark;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date createTime;

	/**
	 * 创建人ID
	 */
	@ApiModelProperty("创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date updateTime;

	/**
	 * 更新人ID
	 */
	@ApiModelProperty("更新人ID")
	private Long updateUser;
}