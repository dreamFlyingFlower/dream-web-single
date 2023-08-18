package com.dream.system.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import dream.framework.web.entity.node.AbstractNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 菜单表DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "菜单表DTO")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO extends AbstractNode<MenuVO, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单名称
	 */
	@Schema(description = "菜单名称")
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;

	/**
	 * 层级,以-拼接
	 */
	private String level;

	/**
	 * 菜单URL
	 */
	@Schema(description = "菜单URL")
	private String url;

	/**
	 * 菜单类型
	 */
	@Schema(description = "类型  0：菜单   1：按钮   2：接口")
	@Range(min = 0, max = 2, message = "类型不正确")
	private Integer type;

	/**
	 * 打开方式
	 */
	@Schema(description = "打开方式   0：内部   1：外部")
	@Range(min = 0, max = 1, message = "打开方式不正确")
	private Integer openStyle;

	/**
	 * 授权标识,多个用逗号分隔
	 */
	@Schema(description = "授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)")
	private String authority;

	/**
	 * 菜单图标
	 */
	@Schema(description = "菜单图标")
	private String icon;

	/**
	 * 排序
	 */
	@Schema(description = "排序")
	private Integer sort;

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

	@Schema(description = "上级名称")
	private String parentName;

	/**
	 * 当前菜单子菜单列表
	 */
	private List<MenuVO> children;

	/**
	 * 当前菜单中的按钮
	 */
	private List<ButtonVO> buttons;
}