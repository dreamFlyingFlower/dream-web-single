package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机号登录
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:40:56
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "手机号登录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileLoginVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "验证码")
	private String code;
}