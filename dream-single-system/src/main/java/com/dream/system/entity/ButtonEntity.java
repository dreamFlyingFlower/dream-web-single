package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import dream.framework.web.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 按钮表 ts_button
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "按钮表 ts_button")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_button")
public class ButtonEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(description = "主键")
	private Long id;

	/**
	 * 按钮标题
	 */
	@Schema(description = "按钮标题")
	private String buttonName;

	/**
	 * 所属菜单ID
	 */
	@Schema(description = "所属菜单ID")
	private Long menuId;

	/**
	 * 链接url
	 */
	@Schema(description = "链接url")
	private String url;

	/**
	 * 图标
	 */
	@Schema(description = "图标")
	private String icon;

	/**
	 * 排序
	 */
	@Schema(description = "排序")
	private Integer sort;
}