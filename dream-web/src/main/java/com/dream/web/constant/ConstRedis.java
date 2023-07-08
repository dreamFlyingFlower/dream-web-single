package com.dream.web.constant;

/**
 * Redis相关常量
 *
 * @author 飞花梦影
 * @date 2022-09-05 15:01:05
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public interface ConstRedis {

	String KEY_FORMAT = "{%s}:{%s}:{%s}";

	/** Redis Key分隔符 */
	String KEY_DELIMITER = ":";

	/** 项目名 */
	String PROJECT_NAME = "dream-basic";

	/** 模块名 */
	String MODULE_NAME = "user";

	/**
	 * 构建普通key
	 * 
	 * @param prefix key前缀
	 * @return 完整key
	 */
	public static String buildKey(String prefix) {
		return String.format(KEY_FORMAT, PROJECT_NAME, MODULE_NAME, prefix);
	}

	/**
	 * 可变参数构建key
	 * 
	 * @param prefixs key数组
	 * @return 完整key
	 */
	public static String buildKey(String... prefixs) {
		return new StringBuilder(PROJECT_NAME).append(KEY_DELIMITER).append(MODULE_NAME).append(KEY_DELIMITER)
		        .toString() + String.join(KEY_DELIMITER, prefixs);
	}
}