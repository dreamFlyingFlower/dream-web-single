package com.dream.system.web.query;

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
 * 菜单表查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "菜单表查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MenuQuery extends AbstractQuery {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
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
}