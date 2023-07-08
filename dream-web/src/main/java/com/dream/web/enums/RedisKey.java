package com.dream.web.enums;

import java.util.stream.Stream;

import com.wy.common.CodeMsg;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis Key前缀
 *
 * @author 飞花梦影
 * @date 2022-09-05 14:58:08
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisKey implements CodeMsg<String> {

	MENU("menu", "菜单Redis Key前缀");

	private String code;

	private String msg;

	public static RedisKey getByCode(String code) {
		return Stream.of(RedisKey.values()).filter(t -> t.code.equals(code)).findFirst().orElse(null);
	}
}