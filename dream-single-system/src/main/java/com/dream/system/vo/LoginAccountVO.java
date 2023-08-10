package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户登录
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 14:54:58
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "账号登录")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAccountVO implements Serializable {

	private static final long serialVersionUID = -2132173723754933687L;

	/**
	 * 用户名
	 */
	@Schema(description = "用户名")
	private String username;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	private String mobile;

	/**
	 * 密码
	 */
	@Schema(description = "密码")
	private String password;

	/**
	 * 域:c-c端用户;b-b端用户
	 */
	@Schema(description = "域:c-c端用户;b-b端用户")
	private String domain;

	/**
	 * 唯一key
	 */
	@Schema(description = "唯一key")
	private String key;

	/**
	 * 验证码
	 */
	@Schema(description = "验证码")
	private String captcha;
}