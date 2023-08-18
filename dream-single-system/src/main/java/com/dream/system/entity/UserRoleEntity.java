package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import dream.framework.web.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户角色关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "用户角色关系")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
public class UserRoleEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private Long userId;

	/**
	 * 角色编码
	 */
	private Long roleId;
}