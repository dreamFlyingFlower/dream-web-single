package com.dream.web.query;

import com.dream.basic.web.query.AbstractQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-08-08 10:05:29
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDataScopeQuery extends AbstractQuery {

	private static final long serialVersionUID = 8082006306195067941L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 机构ID
	 */
	private Long orgId;
}