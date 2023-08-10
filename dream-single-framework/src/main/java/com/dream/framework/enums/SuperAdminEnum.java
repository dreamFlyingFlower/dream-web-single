package com.dream.framework.enums;

import java.util.stream.Stream;

import com.wy.common.StatusMsg;

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
public enum SuperAdminEnum implements StatusMsg {

	YES(1, "是"),
	NO(0, "否");

	private Integer code;

	private String msg;

	public static SuperAdminEnum getByCode(int code) {
		return Stream.of(SuperAdminEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(null);
	}

	public static SuperAdminEnum getByCode(String code) {
		return Stream.of(SuperAdminEnum.values()).filter(t -> t.code.toString().equals(code)).findFirst().orElse(null);
	}

	public static String getMsgByValue(int code) {
		return Stream.of(SuperAdminEnum.values()).filter(t -> t.code.intValue() == code).findFirst().orElse(NO).msg;
	}

	public static Integer getValueByMsg(String msg) {
		return Stream.of(SuperAdminEnum.values()).filter(t -> t.msg.equals(msg)).findFirst().orElse(NO).code;
	}
}