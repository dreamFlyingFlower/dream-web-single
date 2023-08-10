package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;
import com.dream.framework.enums.DataScopeEnum;
import com.wy.annotation.Unique;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "角色信息 ts_role")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_role")
public class RoleEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色编码
	 */
	@Unique
	private String roleCode;

	/**
	 * 角色类型:1-超级管理员;2-普通角色
	 */
	private Integer roleType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 数据范围 {@link DataScopeEnum}
	 */
	private Integer dataScope;

	/**
	 * 机构ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long orgId;
}