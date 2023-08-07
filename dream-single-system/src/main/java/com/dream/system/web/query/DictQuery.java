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
 * 查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictQuery extends AbstractQuery {

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
	private String dictCode;

	/**
	 * 字典名称
	 */
	@ApiModelProperty("字典名称")
	private String dictName;
}