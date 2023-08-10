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
 * ts_dict_item
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = " ts_dict_item")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_item")
public class DictItemEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典ID
	 */
	private Long dictId;

	/**
	 * 字典编码
	 */
	private String dictCode;

	/**
	 * 字典值
	 */
	private String dictValue;

	/**
	 * 字典名
	 */
	private String dictName;

	/**
	 * 字典名样式
	 */
	private String labelClass;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 排序
	 */
	private Integer sort;
}