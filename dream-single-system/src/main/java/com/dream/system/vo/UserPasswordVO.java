package com.dream.system.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户修改密码
 * 
 * @author 飞花梦影
 * @date 2023-08-08 09:52:09
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "用户修改密码")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "原密码", required = true)
	@NotBlank(message = "原密码不能为空")
	private String password;

	@Schema(description = "新密码，密码长度为 4-20 位", required = true)
	@Length(min = 4, max = 20, message = "新密码长度为 4-20 位")
	private String newPassword;

}