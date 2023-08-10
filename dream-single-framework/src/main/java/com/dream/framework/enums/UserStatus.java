package com.dream.framework.enums;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:10:52
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserStatus {

	DISABLE(0, "停用"),
	ENABLED(1, "正常");

	private final Integer code;

	private final String msg;

	public static UserStatus getByCode(int code) {
		return Stream.of(UserStatus.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static UserStatus getByCode(String code) {
		return Stream.of(UserStatus.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}

	public static String getMsgByValue(int code) {
		return Stream.of(UserStatus.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(DISABLE).msg;
	}

	public static Integer getValueByMsg(String msg) {
		return Stream.of(UserStatus.values()).filter(t -> t.msg.equals(msg)).findFirst().orElse(DISABLE).code;
	}
}