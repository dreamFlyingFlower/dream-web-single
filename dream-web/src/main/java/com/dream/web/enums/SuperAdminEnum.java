package com.dream.web.enums;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 超级管理员枚举
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:15:43
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuperAdminEnum {

	/**
	 * 是
	 */
	YES(1, "是"),
	/**
	 * 否
	 */
	NO(0, "否");

	private final Integer value;

	private final String name;

	public static String getNameByValue(int value) {
		for (SuperAdminEnum s : SuperAdminEnum.values()) {
			if (s.getValue() == value) {
				return s.getName();
			}
		}
		return "";
	}

	public static Integer getValueByName(String name) {
		for (SuperAdminEnum s : SuperAdminEnum.values()) {
			if (Objects.equals(s.getName(), name)) {
				return s.getValue();
			}
		}
		return null;
	}
}