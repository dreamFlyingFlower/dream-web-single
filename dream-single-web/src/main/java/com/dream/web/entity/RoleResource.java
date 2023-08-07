package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 角色-权限关系 ts_role_resource
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "角色-权限关系 ts_role_resource")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_role_resource")
public class RoleResource extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 角色id
	 */
	@ApiModelProperty("角色id")
	private Long roleId;

	/**
	 * 资源id
	 */
	@ApiModelProperty("资源id")
	private Long resourceId;

	/**
	 * 资源类型:1-菜单;2-按钮;3数据
	 */
	@ApiModelProperty("资源类型:1-菜单;2-按钮;3数据")
	private Integer resourceType;

	/** 非数据库字段 */
}