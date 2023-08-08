package com.dream.web.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片验证码
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:57:31
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "图片验证码")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "key")
	private String key;

	@Schema(description = "image base64")
	private String image;
}