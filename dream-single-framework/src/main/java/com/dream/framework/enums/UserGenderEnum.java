package com.dream.framework.enums;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户性别
 *
 * @author 飞花梦影
 * @date 2023-08-10 11:36:37
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserGenderEnum {

	/**
	 * 男
	 */
	MAN(0, "男"),
	/**
	 * 女
	 */
	WOMEN(1, "女"),
	/**
	 * 未知
	 */
	UNKNOWN(2, "未知");

	private final Integer code;

	private final String msg;

	public static UserGenderEnum getByCode(int code) {
		return Stream.of(UserGenderEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static UserGenderEnum getByCode(String code) {
		return Stream.of(UserGenderEnum.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}

	public static String getMsgByValue(int code) {
		return Stream.of(UserGenderEnum.values()).filter(t -> t.code.intValue() == code).findFirst()
				.orElse(UNKNOWN).msg;
	}

	public static Integer getValueByMsg(String msg) {
		return Stream.of(UserGenderEnum.values()).filter(t -> t.msg.equals(msg)).findFirst().orElse(UNKNOWN).code;
	}
}