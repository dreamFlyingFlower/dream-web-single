package com.dream.system.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-权限关系DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "角色-权限关系DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceVO {

	/**
	 * 主键
	 */
	@Schema(description = "主键")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 角色id
	 */
	@Schema(description = "角色id")
	private Long roleId;

	/**
	 * 资源id
	 */
	@Schema(description = "资源id")
	private Long resourceId;

	/**
	 * 资源类型:1-菜单;2-按钮;3数据
	 */
	@Schema(description = "资源类型:1-菜单;2-按钮;3数据")
	private Integer resourceType;

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