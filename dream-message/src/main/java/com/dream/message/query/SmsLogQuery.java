package com.dream.message.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信日志查询
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:01:35
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "短信日志查询")
public class SmsLogQuery extends AbstractQuery {

	private static final long serialVersionUID = -4383991909538414037L;

	@Schema(description = "平台ID")
	private Long platformId;

	@Schema(description = "平台类型")
	private Integer platform;

}