package com.dream.system.query;

import java.time.LocalDateTime;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "按钮表查询参数")
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