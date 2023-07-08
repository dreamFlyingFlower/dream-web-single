package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:28:08
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ts_role_org")
public class RoleOrg {

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 机构ID
	 */
	private Long orgId;
}