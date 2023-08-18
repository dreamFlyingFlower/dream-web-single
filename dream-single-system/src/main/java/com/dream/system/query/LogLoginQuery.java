package com.dream.system.query;

import dream.framework.web.query.AbstractQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 登录日志查询
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "登录日志查询")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LogLoginQuery extends AbstractQuery {

	private static final long serialVersionUID = 5020664657051512184L;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "登录地点")
	private String address;

	@Schema(description = "登录状态  0：失败   1：成功")
	private Integer status;
}