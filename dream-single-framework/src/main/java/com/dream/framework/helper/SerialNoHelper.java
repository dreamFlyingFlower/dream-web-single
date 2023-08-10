package com.dream.framework.helper;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.dream.framework.cache.RedisKeys;
import com.wy.lang.StrTool;

/**
 * 序列号生成工具类
 *
 * @author 飞花梦影
 * @date 2023-05-29 15:46:21
 */
@Component
public class SerialNoHelper {

	private static final int DEFAULT_NO_LENGTH = 6;

	private static RedisTemplate<String, Object> redisTemplate;

	@Autowired
	public void setDictService(RedisTemplate<String, Object> redisInitTemplate) {
		SerialNoHelper.redisTemplate = redisInitTemplate;
	}

	/**
	 * 生成序列号,所有租户共用序列
	 * 
	 * @param prefix 编码前缀
	 * @param length 编码长度,不包含前缀
	 * @return 序列编码
	 */
	public static String generateNo(String prefix, int length) {
		Long index = redisTemplate.opsForValue().increment(RedisKeys.buildKey(prefix), 1L);
		String format = StringUtils.repeat("0", length);
		DecimalFormat numberFormat = new DecimalFormat(format);
		return prefix + numberFormat.format(index);
	}

	/**
	 * 生成序列号,默认6位,不包含前缀
	 * 
	 * @param tenantId 租户编号
	 * @param prefix 编码前缀
	 * @return 序列编码
	 */
	public static String generateNo(String tenantId, String prefix) {
		return generateNo(tenantId, prefix, DEFAULT_NO_LENGTH);
	}

	/**
	 * 生成序列号
	 * 
	 * @param tenantId 租户编号
	 * @param prefix 编码前缀
	 * @param length 编码长度,不包含前缀
	 * @return 序列编码
	 */
	public static String generateNo(String tenantId, String prefix, int length) {
		Long index = redisTemplate.opsForValue().increment(RedisKeys.buildKey(tenantId + ":" + prefix), 1L);
		String format = StringUtils.repeat("0", length);
		DecimalFormat numberFormat = new DecimalFormat(format);
		return prefix + numberFormat.format(index);
	}

	/**
	 * 带当前日期的序列号
	 * 
	 * @param key 业务前缀
	 * @return
	 */
	public static String generateNoWithDate(String key) {
		return generateNoWithDate(key, DEFAULT_NO_LENGTH);
	}

	/**
	 * 带当前日期的序列号
	 * 
	 * @param key 业务前缀
	 * @return
	 */
	public static String generateNoWithDate(String key, int length) {
		String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String redisKey = RedisKeys.buildKey(key + localDate);
		Long index = redisTemplate.opsForValue().increment(redisKey);
		if (1 == index) {
			// FIXME 将自增和设置超时改为lua脚本 (飞花梦影,2022-08-17,[2022-08-17])
			redisTemplate.expire(redisKey, Duration.ofDays(1));
		}
		String format = StringUtils.repeat("0", length);
		DecimalFormat numberFormat = new DecimalFormat(format);
		return key + localDate + numberFormat.format(index);
	}

	/**
	 * 带当前日期的序列号
	 * 
	 * @param tenantId 租户编码
	 * @param key 业务前缀
	 * @return
	 */
	public static String generateNoWithDate(String tenantId, String key) {
		return generateNoWithDate(tenantId, key, DEFAULT_NO_LENGTH);
	}

	/**
	 * 带当前日期的序列号
	 * 
	 * @param tenantId 租户编码
	 * @param key 业务前缀
	 * @param length 编码长度,不包含前缀
	 * @return
	 */
	public static String generateNoWithDate(String tenantId, String key, int length) {
		String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String redisKey = RedisKeys.buildKey(tenantId + RedisKeys.KEY_DELIMITER + key + localDate);
		Long index = redisTemplate.opsForValue().increment(redisKey);
		if (1 == index) {
			// FIXME 将自增和设置超时改为lua脚本 (飞花梦影,2022-08-17,[2022-08-17])
			redisTemplate.expire(redisKey, Duration.ofDays(1));
		}
		String format = StringUtils.repeat("0", length);
		DecimalFormat numberFormat = new DecimalFormat(format);
		return key + localDate + numberFormat.format(index);
	}

	/**
	 * 批量获得序列号
	 * 
	 * @param key 业务前缀
	 * @return 格式如key20230529000001
	 */
	public static List<String> generateNosWithDate(String key) {
		return generateNosWithDate(key, 10);
	}

	/**
	 * 批量获得序列号
	 * 
	 * @param key 业务前缀
	 * @param delta 批量数
	 * @return 格式如key20230529000001
	 */
	public static List<String> generateNosWithDate(String key, long delta) {
		delta = delta < 1 ? 1 : delta;
		String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String redisKey = RedisKeys.buildKey(key + localDate);
		Object object = redisTemplate.opsForValue().get(redisKey);
		Long begin = Objects.isNull(object) ? 0L : Long.parseLong(String.valueOf(object));
		Long end = redisTemplate.opsForValue().increment(redisKey, delta);
		if (Objects.isNull(object)) {
			// FIXME 将自增和设置超时改为lua脚本 (飞花梦影,2022-08-17,[2022-08-17])
			redisTemplate.expire(redisKey, Duration.ofDays(1));
		}
		DecimalFormat numberFormat = new DecimalFormat("000000");
		List<String> orderNos = new ArrayList<>();
		for (long i = begin + 1; i <= end; i++) {
			orderNos.add(key + localDate + numberFormat.format(i));
		}
		return orderNos;
	}

	/**
	 * 创建以时间为依据的流水序号
	 *
	 * @param prefix 序号特殊标识
	 * @return 流水序号
	 */
	static String generateSerialNumber(String prefix) {
		String result = null;
		String random = String.format("%03d", ThreadLocalRandom.current().nextInt(1, 999));
		if (StrTool.isNotBlank(prefix)) {
			String nowTime = prefix + "-" + System.currentTimeMillis();
			result = nowTime + random;
		} else {
			result = System.currentTimeMillis() + random;
		}
		return result;
	}
}