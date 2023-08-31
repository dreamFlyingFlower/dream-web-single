package com.dream.system.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dream.framework.cache.RedisCache;
import com.dream.framework.cache.RedisKeys;
import com.dream.framework.enums.SysParamsEnum;
import com.dream.system.service.CaptchaService;
import com.dream.system.service.ParamsService;
import com.dream.system.vo.CaptchaVO;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wy.lang.StrHelper;

import lombok.AllArgsConstructor;

/**
 * 验证码
 *
 * @author 飞花梦影
 * @date 2023-08-08 18:00:49
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

	private final RedisCache redisCache;

	private final ParamsService paramsService;

	@Override
	public CaptchaVO generate() {
		// 生成验证码key
		String key = UUID.randomUUID().toString();

		// 生成验证码
		SpecCaptcha captcha = new SpecCaptcha(150, 40);
		captcha.setLen(5);
		captcha.setCharType(Captcha.TYPE_DEFAULT);
		String image = captcha.toBase64();

		// 保存到缓存
		String redisKey = RedisKeys.getCaptchaKey(key);
		redisCache.set(redisKey, captcha.text(), 300);

		// 封装返回数据
		CaptchaVO captchaVO = new CaptchaVO();
		captchaVO.setKey(key);
		captchaVO.setImage(image);

		return captchaVO;
	}

	@Override
	public boolean validate(String key, String code) {
		// 如果关闭了验证码,则直接效验通过
		if (!isCaptchaEnabled()) {
			return true;
		}

		if (StrHelper.isBlank(key) || StrHelper.isBlank(code)) {
			return false;
		}

		// 获取验证码
		String captcha = getCache(key);

		// 效验成功
		return code.equalsIgnoreCase(captcha);
	}

	private String getCache(String key) {
		key = RedisKeys.getCaptchaKey(key);
		String captcha = (String) redisCache.get(key);
		// 删除验证码
		if (captcha != null) {
			redisCache.delete(key);
		}

		return captcha;
	}

	/**
	 * 是否开启登录验证码
	 *
	 * @return true-开启;false-关闭
	 */
	private boolean isCaptchaEnabled() {
		return paramsService.getBoolean(SysParamsEnum.LOGIN_CAPTCHA.name());
	}
}