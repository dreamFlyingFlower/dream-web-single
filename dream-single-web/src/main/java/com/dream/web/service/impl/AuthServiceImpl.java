package com.dream.web.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dream.basic.core.constant.ConstCore;
import com.dream.system.enums.LoginOperationEnum;
import com.dream.system.manager.SmsManager;
import com.dream.system.utils.TokenUtils;
import com.dream.web.cache.TokenStoreCache;
import com.dream.web.oauth.mobile.MobileAuthenticationToken;
import com.dream.web.security.SecurityUserDetails;
import com.dream.web.service.AuthService;
import com.dream.web.service.CaptchaService;
import com.dream.web.service.LogLoginService;
import com.dream.web.service.UserService;
import com.dream.web.vo.LoginAccountVO;
import com.dream.web.vo.MobileLoginVO;
import com.dream.web.vo.TokenVO;
import com.dream.web.vo.UserVO;
import com.wy.result.ResultException;
import com.wy.util.RandomTool;

import lombok.AllArgsConstructor;

/**
 * 权限认证服务
 *
 * @author 飞花梦影
 * @date 2023-08-08 23:31:49
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final CaptchaService sysCaptchaService;

	private final TokenStoreCache tokenStoreCache;

	private final AuthenticationManager authenticationManager;

	private final LogLoginService sysLogLoginService;

	private final UserService sysUserService;

	private final SmsManager smsApi;

	@Override
	public TokenVO loginByAccount(LoginAccountVO login) {
		// 验证码效验
		boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
		if (!flag) {
			// 保存登录日志
			sysLogLoginService.save(login.getUsername(), ConstCore.FAIL, LoginOperationEnum.CAPTCHA_FAIL.getValue());

			throw new ResultException("验证码错误");
		}

		Authentication authentication;
		try {
			// 用户认证
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ResultException("用户名或密码错误");
		}

		// 用户信息
		SecurityUserDetails user = (SecurityUserDetails) authentication.getPrincipal();

		// 生成 accessToken
		String accessToken = TokenUtils.generator();

		// 保存用户信息到缓存
		tokenStoreCache.saveUser(accessToken, user);

		return new TokenVO(accessToken);
	}

	@Override
	public TokenVO loginByMobile(MobileLoginVO login) {
		Authentication authentication;
		try {
			// 用户认证
			authentication = authenticationManager
					.authenticate(new MobileAuthenticationToken(login.getMobile(), login.getCode()));
		} catch (BadCredentialsException e) {
			throw new ResultException("手机号或验证码错误");
		}

		// 用户信息
		SecurityUserDetails user = (SecurityUserDetails) authentication.getPrincipal();

		// 生成 accessToken
		String accessToken = TokenUtils.generator();

		// 保存用户信息到缓存
		tokenStoreCache.saveUser(accessToken, user);

		return new TokenVO(accessToken);
	}

	@Override
	public boolean sendCode(String mobile) {
		// 生成6位验证码
		String code = RandomTool.randomNumeric(6);
		UserVO user = sysUserService.getByMobile(mobile);
		if (user == null) {
			throw new ResultException("手机号未注册");
		}
		// 发送短信
		return smsApi.sendCode(mobile, "code", code);
	}

	@Override
	public void logout(String accessToken) {
		// 用户信息
		SecurityUserDetails user = tokenStoreCache.getUser(accessToken);

		// 删除用户信息
		tokenStoreCache.deleteUser(accessToken);

		// 保存登录日志
		sysLogLoginService.save(user.getUsername(), ConstCore.SUCCESS, LoginOperationEnum.LOGOUT_SUCCESS.getValue());
	}
}