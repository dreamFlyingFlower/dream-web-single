package com.dream.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 *
 * @author 飞花梦影
 * @date 2023-07-09 00:12:19
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DataScopeEnum {

	/**
	 * 全部数据
	 */
	ALL(0),
	/**
	 * 本机构及子机构数据
	 */
	ORG_AND_CHILD(1),
	/**
	 * 本机构数据
	 */
	ORG_ONLY(2),
	/**
	 * 本人数据
	 */
	SELF(3),
	/**
	 * 自定义数据
	 */
	CUSTOM(4);

	private final Integer value;

}