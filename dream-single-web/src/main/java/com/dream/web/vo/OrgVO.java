package com.dream.web.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import com.dream.system.entity.TreeNode;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 组织机构表DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "组织机构表DTO")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrgVO extends TreeNode<OrgVO> {

	private static final long serialVersionUID = 1L;

	/**
	 * 组织机构编码
	 */
	@ApiModelProperty("组织机构编码")
	private String orgCode;

	/**
	 * 组织机构简称
	 */
	@ApiModelProperty("组织机构简称")
	private String orgName;

	/**
	 * 组织机构层级,由高到低,以-相连
	 */
	@ApiModelProperty("组织机构层级,由高到低,以-相连")
	private String level;

	/**
	 * 父级名称
	 */
	@Schema(defaultValue = "父级名称")
	private String parentName;

	/**
	 * 排序
	 */
	@Schema(description = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;

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
	 * 最新一次更新时间
	 */
	@ApiModelProperty("最新一次更新时间")
	private LocalDateTime updateTime;

	/**
	 * 最近一次更新用户ID
	 */
	@ApiModelProperty("最近一次更新用户ID")
	private Long updateUser;
}