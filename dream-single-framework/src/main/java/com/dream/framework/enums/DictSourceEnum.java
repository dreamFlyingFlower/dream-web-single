package com.dream.framework.enums;

import java.util.stream.Stream;

import com.wy.common.StatusMsg;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典数据来源
 *
 * @author 飞花梦影
 * @date 2023-08-10 11:24:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DictSourceEnum implements StatusMsg {

	DICT(0, "字典数据"),
	SQL(1, "动态SQL");

	private Integer code;

	private String msg;

	public static DictSourceEnum getByCode(int code) {
		return Stream.of(DictSourceEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static DictSourceEnum getByCode(String code) {
		return Stream.of(DictSourceEnum.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}
}