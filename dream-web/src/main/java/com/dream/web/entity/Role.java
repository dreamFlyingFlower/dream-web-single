package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;
import com.wy.annotation.Unique;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 角色信息 ts_role
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "角色信息 ts_role")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_role")
public class Role extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 角色名称
	 */
	@ApiModelProperty("角色名称")
	private String roleName;

	/**
	 * 角色编码
	 */
	@ApiModelProperty("角色编码")
	@Unique
	private String roleCode;

	/**
	 * 角色类型:1-超级管理员;2-普通角色
	 */
	@ApiModelProperty("角色类型:1-超级管理员;2-普通角色")
	private Integer roleType;

	/** 非数据库字段 */
}