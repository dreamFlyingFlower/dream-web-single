package com.dream.system.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * @date 2023-07-09 00:28:08
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "角色数据权限查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleOrgQuery extends AbstractQuery {

	private static final long serialVersionUID = 4670960392058567276L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 机构ID
	 */
	private Long orgId;
}