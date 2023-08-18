package com.dream.system.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import dream.framework.web.valid.ValidEdit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账号-角色关系DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "账号-角色关系DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVO {

	/**
	 * 主键
	 */
	@Schema(description = "主键")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private Long userId;

	/**
	 * 角色编码
	 */
	@Schema(description = "角色编码")
	private Long roleId;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@Schema(description = "创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 更新人ID
	 */
	@Schema(description = "更新人ID")
	private Long updateUser;
}