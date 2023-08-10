package com.dream.framework.enums;

import java.util.stream.Stream;

import com.wy.common.StatusMsg;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:15:34
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MenuType implements StatusMsg {

	MENU(0, "菜单"),
	BUTTON(1, "按钮"),
	INTERFACE(2, "接口");

	private Integer code;

	private String msg;

	public static MenuType getByCode(int code) {
		return Stream.of(MenuType.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static MenuType getByCode(String code) {
		return Stream.of(MenuType.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}
}