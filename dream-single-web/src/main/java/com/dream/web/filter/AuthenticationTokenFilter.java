package com.dream.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dream.system.utils.TokenUtils;
import com.dream.web.cache.TokenStoreCache;
import com.dream.web.security.UserDetail;
import com.wy.lang.StrTool;

/**
 * 认证过滤器
 *
 * @author 飞花梦影
 * @date 2023-07-08 17:06:23
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenStoreCache tokenStoreCache;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String accessToken = TokenUtils.getAccessToken(request);
		// accessToken为空,表示未登录
		if (StrTool.isBlank(accessToken)) {
			chain.doFilter(request, response);
			return;
		}

		// 获取登录用户信息
		UserDetail user = tokenStoreCache.getUser(accessToken);
		if (user == null) {
			chain.doFilter(request, response);
			return;
		}

		// 用户存在
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		// 新建 SecurityContext
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);

		chain.doFilter(request, response);
	}
}