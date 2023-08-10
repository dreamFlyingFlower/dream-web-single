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
 * 参数管理
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "参数管理")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_params")
public class ParamsEntity extends AbstractEntity {

	private static final long serialVersionUID = 9161720926235407181L;

	/**
	 * 参数名称
	 */
	private String paramName;

	/**
	 * 系统参数
	 */
	private Integer paramType;

	/**
	 * 参数键
	 */
	@Unique
	private String paramKey;

	/**
	 * 参数值
	 */
	private String paramValue;

	/**
	 * 备注
	 */
	private String remark;
}