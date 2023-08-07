package com.dream.message.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 短信日志查询
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:01:35
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "短信日志查询")
public class SmsLogQuery extends AbstractQuery {

	private static final long serialVersionUID = -4383991909538414037L;

	@Schema(description = "平台ID")
	private Long platformId;

	@Schema(description = "平台类型")
	private Integer platform;

}