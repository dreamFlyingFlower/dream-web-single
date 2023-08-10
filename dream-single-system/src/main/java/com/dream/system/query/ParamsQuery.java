package com.dream.system.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 参数管理查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "参数管理查询")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ParamsQuery extends AbstractQuery {

	private static final long serialVersionUID = 2242689650356524428L;

	@Schema(description = "系统参数")
	private Integer paramType;

	@Schema(description = "参数键")
	private String paramKey;

	@Schema(description = "参数值")
	private String paramValue;
}