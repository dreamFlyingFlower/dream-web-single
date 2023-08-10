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
 * 字典
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "字典")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict")
public class DictEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典编码
	 */
	@Unique
	private String dictCode;

	/**
	 * 字典名称
	 */
	private String dictName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 来源:0-字典数据;1-动态SQL
	 */
	private Integer dictSource;

	/**
	 * 动态sql
	 */
	private String dictSql;
}