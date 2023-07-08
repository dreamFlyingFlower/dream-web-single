package com.dream.web.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单表DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "菜单表DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 菜单标题
	 */
	@ApiModelProperty("菜单标题")
	private String menuName;

	/**
	 * 父id
	 */
	@ApiModelProperty("父id")
	private Long pid;

	/**
	 * 层级,以-拼接
	 */
	private String level;

	/**
	 * 链接url
	 */
	@ApiModelProperty("链接url")
	private String url;

	/**
	 * 图标
	 */
	@ApiModelProperty("图标")
	private String icon;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer sort;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@ApiModelProperty("创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * 更新人ID
	 */
	@ApiModelProperty("更新人ID")
	private Long updateUser;

	/**
	 * 当前菜单子菜单列表
	 */
	private List<MenuDTO> children;

	/**
	 * 当前菜单中的按钮
	 */
	private List<ButtonDTO> buttons;
}