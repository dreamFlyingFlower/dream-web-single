package com.dream.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.BaseController;
import com.dream.framework.helper.TokenHelper;
import com.dream.system.service.AuthService;
import com.dream.system.service.CaptchaService;
import com.dream.system.vo.CaptchaVO;
import com.dream.system.vo.LoginAccountVO;
import com.dream.system.vo.MobileLoginVO;
import com.dream.system.vo.TokenVO;
import com.wy.result.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 认证管理API
 *
 * @author 飞花梦影
 * @date 2023-08-09 15:35:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/auth")
@Tag(name = "认证管理API")
@AllArgsConstructor
public class AuthController implements BaseController {

	private final CaptchaService captchaService;

	private final AuthService authService;

	@GetMapping("captcha")
	@Operation(summary = "验证码")
	public Result<CaptchaVO> captcha() {
		return ok(captchaService.generate());
	}

	@PostMapping("login")
	@Operation(summary = "账号密码登录")
	public Result<TokenVO> login(@RequestBody LoginAccountVO login) {
		return ok(authService.loginByAccount(login));
	}

	@PostMapping("send/code")
	@Operation(summary = "发送短信验证码")
	public Result<String> sendCode(String mobile) {
		boolean flag = authService.sendCode(mobile);
		if (!flag) {
			return error("短信发送失败！");
		}
		return ok();
	}

	@PostMapping("mobile")
	@Operation(summary = "手机号登录")
	public Result<TokenVO> mobile(@RequestBody MobileLoginVO login) {
		return ok(authService.loginByMobile(login));
	}

	@PostMapping("logout")
	@Operation(summary = "退出")
	public Result<String> logout(HttpServletRequest request) {
		authService.logout(TokenHelper.getAccessToken(request));
		return ok();
	}
}