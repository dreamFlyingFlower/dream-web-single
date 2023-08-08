package com.dream.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户Token
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:50:22
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "用户登录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "access_token")
	private String access_token;
}