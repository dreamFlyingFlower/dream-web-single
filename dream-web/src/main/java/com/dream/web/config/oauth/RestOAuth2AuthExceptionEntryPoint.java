package com.dream.web.config.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

import com.dream.basic.core.helper.WebHelper;
import com.wy.result.Result;

/**
 * 登录异常处理
 *
 * @author 飞花梦影
 * @date 2023-07-08 16:42:54
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public class RestOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		WebHelper.write(response, Result.error(e.getMessage()));
	}
}