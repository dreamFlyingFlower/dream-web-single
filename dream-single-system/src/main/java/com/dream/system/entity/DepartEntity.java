package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;
import com.wy.annotation.Unique;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 部门表 ts_depart
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "部门表 ts_depart")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_depart")
public class DepartEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@Schema(description = "主键ID")
	private Long id;

	/**
	 * 部门编码
	 */
	@Schema(description = "部门编码")
	@Unique
	private String departCode;

	/**
	 * 部门名称
	 */
	@Schema(description = "部门名称")
	private String departName;

	/**
	 * 上级部门ID,顶层部门为0
	 */
	@Schema(description = "上级部门ID,顶层部门为0")
	private Long pid;

	/**
	 * 部门层级,由高到低,以-相连
	 */
	@Schema(description = "部门层级,由高到低,以-相连")
	private String level;
}