package com.dream.framework.xss;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.HTMLFilter;

/**
 * XSS 过滤工具类
 *
 * @author 飞花梦影
 * @date 2023-08-10 15:04:16
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public class XssUtils {

	private static final ThreadLocal<HTMLFilter> HTML_FILTER = ThreadLocal.withInitial(() -> {
		HTMLFilter htmlFilter = new HTMLFilter();
		// 避免 " 被转成 &quot; 字符
		ReflectUtil.setFieldValue(htmlFilter, "encodeQuotes", false);
		return htmlFilter;
	});

	/**
	 * XSS过滤
	 *
	 * @param content 需要过滤的内容
	 * @return 返回过滤后的内容
	 */
	public static String filter(String content) {
		return HTML_FILTER.get().filter(content);
	}
}