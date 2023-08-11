package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 菜单
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "菜单")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class MenuEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单标题
	 */
	private String menuName;

	/**
	 * 父id
	 */
	private Long pid;

	/**
	 * 层级,以-拼接
	 */
	private String level;

	/**
	 * 链接url
	 */
	private String url;

	/**
	 * 授权标识(多个用逗号分隔,如:sys:menu:list,sys:menu:save)
	 */
	private String authority;

	/**
	 * 类型:1-菜单;2-按钮;3-接口
	 */
	private Integer type;

	/**
	 * 打开方式:0-内部;1-外部
	 */
	private Integer openStyle;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer sort;
}