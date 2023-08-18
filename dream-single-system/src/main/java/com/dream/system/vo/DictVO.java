package com.dream.system.vo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wy.ConstDate;

import dream.framework.web.valid.ValidEdit;
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
@Schema(description = "字典DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictVO {

	/**
	 * 主键
	 */
	@Schema(description = "主键")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 字典编码
	 */
	@Schema(description = "字典编码")
	@NotBlank(message = "字典编码不能为空")
	private String dictCode;

	/**
	 * 字典名称
	 */
	@Schema(description = "字典名称")
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
	@Schema(description = "创建时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date createTime;

	/**
	 * 创建人ID
	 */
	@Schema(description = "创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date updateTime;

	/**
	 * 更新人ID
	 */
	@Schema(description = "更新人ID")
	private Long updateUser;

	@Schema(description = "字典数据列表")
	private List<DictItemVO> dictItemVOs;
}