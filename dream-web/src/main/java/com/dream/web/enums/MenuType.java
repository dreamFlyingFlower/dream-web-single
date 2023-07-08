package com.dream.web.enums;

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
public enum MenuType {

	/**
	 * 菜单
	 */
	MENU(0),
	/**
	 * 按钮
	 */
	BUTTON(1),
	/**
	 * 接口
	 */
	INTERFACE(2);

	private final int value;

}