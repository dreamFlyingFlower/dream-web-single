package com.dream.system.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门表DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "部门表DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartVO {

	/**
	 * 主键ID
	 */
	@Schema(description = "主键ID")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 部门编码
	 */
	@Schema(description = "部门编码")
	private String departCode;

	/**
	 * 部门名称
	 */
	@Schema(description = "部门名称")
	private String departName;

	/**
	 * 上级部门ID,顶层部门为0
	 */
	@Schema(description = "上级部门ID,顶层部门为0")
	private Long pid;

	/**
	 * 部门层级,由高到低,以-相连
	 */
	@Schema(description = "部门层级,由高到低,以-相连")
	private String level;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@Schema(description = "创建人ID")
	private Long createUser;

	/**
	 * 最新一次更新时间
	 */
	@Schema(description = "最新一次更新时间")
	private LocalDateTime updateTime;

	/**
	 * 最近一次更新用户ID
	 */
	@Schema(description = "最近一次更新用户ID")
	private Long updateUser;
}