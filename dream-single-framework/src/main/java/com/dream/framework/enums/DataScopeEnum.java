package com.dream.framework.enums;

import java.util.stream.Stream;

import com.wy.common.StatusMsg;

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
public enum DataScopeEnum implements StatusMsg {

	ALL(0, "全局数据"),
	ORG_AND_CHILD(1, "本机构及子机构数据"),
	ORG_ONLY(2, "本机构数据"),
	SELF(3, "本人数据"),
	CUSTOM(4, "自定义数据");

	private Integer code;

	private String msg;

	public static DataScopeEnum getByCode(int code) {
		return Stream.of(DataScopeEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static DataScopeEnum getByCode(String code) {
		return Stream.of(DataScopeEnum.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}
}