package com.dream.system.web.query;

import java.time.LocalDateTime;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 按钮表查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "按钮表查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ButtonQuery extends AbstractQuery {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 按钮标题
	 */
	@ApiModelProperty("按钮标题")
	private String buttonName;

	/**
	 * 所属菜单ID
	 */
	@ApiModelProperty("所属菜单ID")
	private Long menuId;

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
}