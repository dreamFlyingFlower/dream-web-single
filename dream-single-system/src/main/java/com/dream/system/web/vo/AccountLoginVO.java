package com.dream.system.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账号登录
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:56:30
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "账号登录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "唯一key")
	private String key;

	@Schema(description = "验证码")
	private String captcha;
}