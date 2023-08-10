package com.dream.framework.enums;

import java.util.stream.Stream;

import com.wy.common.StatusMsg;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录信息
 *
 * @author 飞花梦影
 * @date 2023-08-10 11:24:34
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginOperationEnum implements StatusMsg {

	LOGIN_SUCCESS(0, "登录成功"),
	LOGOUT_SUCCESS(1, "退出成功"),
	CAPTCHA_FAIL(2, "验证码错误"),
	ACCOUNT_FAIL(3, "账号密码错误");

	private Integer code;

	private String msg;

	public static LoginOperationEnum getByCode(int code) {
		return Stream.of(LoginOperationEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static LoginOperationEnum getByCode(String code) {
		return Stream.of(LoginOperationEnum.values()).filter(t -> t.code.toString().equals(code)).findFirst()
				.orElse(null);
	}
}