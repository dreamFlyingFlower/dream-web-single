package com.dream.message.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信平台查询
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:02:20
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "短信平台查询")
public class SmsPlatformQuery extends AbstractQuery {

	private static final long serialVersionUID = 6245397091128266813L;

	@Schema(description = "平台类型  0：阿里云   1：腾讯云")
	private Integer platform;

	@Schema(description = "短信签名")
	private String signName;
}