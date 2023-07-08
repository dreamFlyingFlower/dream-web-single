package com.dream.web.enums;

import java.util.Objects;

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

	/**
	 * 停用
	 */
	DISABLE(0, "停用"),
	/**
	 * 正常
	 */
	ENABLED(1, "正常");

	private int value;

	private String name;

	public static String getNameByValue(int value) {
		for (UserStatus s : UserStatus.values()) {
			if (s.getValue() == value) {
				return s.getName();
			}
		}
		return "";
	}

	public static Integer getValueByName(String name) {
		for (UserStatus s : UserStatus.values()) {
			if (Objects.equals(s.getName(), name)) {
				return s.getValue();
			}
		}
		return null;
	}
}