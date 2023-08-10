package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:28:08
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "角色权限")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleOrgVO implements Serializable {

	private static final long serialVersionUID = 3601263566670528295L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 机构ID
	 */
	private Long orgId;
}