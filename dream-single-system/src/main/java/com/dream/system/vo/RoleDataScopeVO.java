package com.dream.system.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色数据权限
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:51:11
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "角色数据权限")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDataScopeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	@NotNull(message = "角色ID不能为空")
	private Long id;

	@Schema(description = "数据范围  0：全部数据  1：本机构及子机构数据  2：本机构数据  3：本人数据  4：自定义数据")
	@NotNull(message = "数据范围不能为空")
	private Integer dataScope;

	@Schema(description = "机构ID列表")
	private List<Long> orgIdList;
}