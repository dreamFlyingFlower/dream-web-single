package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;
import com.wy.db.annotation.Unique;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * ts_dict
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = " ts_dict")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ts_dict")
public class Dict extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 字典编码
	 */
	@ApiModelProperty("字典编码")
	@Unique
	private String dictCode;

	/**
	 * 字典名称
	 */
	@ApiModelProperty("字典名称")
	private String dictName;

	/** 非数据库字段 */
}