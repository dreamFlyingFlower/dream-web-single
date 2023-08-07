package com.dream.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import com.electric.framework.security.utils.TokenUtils;
import com.electric.framework.utils.Result;
import com.electric.system.service.SysAuthService;
import com.electric.system.service.SysCaptchaService;
import com.electric.system.vo.SysAccountLoginVO;
import com.electric.system.vo.SysCaptchaVO;
import com.electric.system.vo.SysMobileLoginVO;
import com.electric.system.vo.SysTokenVO;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证管理
 *
 * @author
 */
@RestController
@RequestMapping("sys/auth")
@Tag(name = "认证管理")
@AllArgsConstructor
public class SysAuthController {

	private final SysCaptchaService sysCaptchaService;

	private final SysAuthService sysAuthService;

	@GetMapping("captcha")
	@Operation(summary = "验证码")
	public Result<SysCaptchaVO> captcha() {
		SysCaptchaVO captchaVO = sysCaptchaService.generate();
		return Result.ok(captchaVO);
	}

	@PostMapping("login")
	@Operation(summary = "账号密码登录")
	public Result<SysTokenVO> login(@RequestBody SysAccountLoginVO login) {
		SysTokenVO token = sysAuthService.loginByAccount(login);
		return Result.ok(token);
	}
	
	@PostMapping("send/code")
	@Operation(summary = "发送短信验证码")
	public Result<String> sendCode(String mobile) {
		boolean flag = sysAuthService.sendCode(mobile);
		if (!flag) {
			return Result.error("短信发送失败！");
		}
		return Result.ok();
	}

	@PostMapping("mobile")
	@Operation(summary = "手机号登录")
	public Result<SysTokenVO> mobile(@RequestBody SysMobileLoginVO login) {
		SysTokenVO token = sysAuthService.loginByMobile(login);
		return Result.ok(token);
	}

	@PostMapping("logout")
	@Operation(summary = "退出")
	public Result<String> logout(HttpServletRequest request) {
		sysAuthService.logout(TokenUtils.getAccessToken(request));
		return Result.ok();
	}
}