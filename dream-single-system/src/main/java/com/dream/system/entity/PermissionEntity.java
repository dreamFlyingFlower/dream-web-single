package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wy.annotation.Unique;

import dream.framework.mybatis.plus.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 权限
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "权限")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_permission")
public class PermissionEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 权限编码
	 */
	@Schema(description = "权限编码")
	@Unique
	private String permissionCode;

	/**
	 * 权限名称
	 */
	@Schema(description = "权限名称")
	private String permissionName;
}