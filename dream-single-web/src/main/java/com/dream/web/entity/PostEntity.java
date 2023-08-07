package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 岗位管理 ts_post
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "岗位管理 ts_post")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_post")
public class PostEntity extends AbstractEntity {

	private static final long serialVersionUID = 5859937060709486043L;

	/**
	 * 岗位编码
	 */
	private String postCode;

	/**
	 * 岗位名称
	 */
	private String postName;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 状态 0：停用 1：正常
	 */
	private Integer status;

	@TableField(exist = false)
	private Long userId;
}