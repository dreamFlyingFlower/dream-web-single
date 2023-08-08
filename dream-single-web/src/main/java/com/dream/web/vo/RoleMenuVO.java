package com.dream.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色菜单关系
 *
 * @author 飞花梦影
 * @date 2023-08-08 10:05:09
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "角色菜单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;
}