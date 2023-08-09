package com.dream.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.BaseController;
import com.dream.system.utils.TokenUtils;
import com.dream.web.service.AuthService;
import com.dream.web.service.CaptchaService;
import com.dream.web.vo.CaptchaVO;
import com.dream.web.vo.LoginAccountVO;
import com.dream.web.vo.MobileLoginVO;
import com.dream.web.vo.TokenVO;
import com.wy.result.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 认证管理
 *
 * @author 飞花梦影
 * @date 2023-08-09 15:35:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/auth")
@Tag(name = "认证管理")
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
		authService.logout(TokenUtils.getAccessToken(request));
		return ok();
	}
}