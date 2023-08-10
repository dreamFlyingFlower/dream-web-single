package com.dream.system.entity;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * 自定义Web认证.由于网页端用户认证(认证码模式,简化模式)会走UsernamePasswordAuthenticationFilter,
 * 把request中的额外信息增加至WebAuthenticationDetails
 * 
 * @author 飞花梦影
 * @date 2022-09-07 15:57:01
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Deprecated
public class IntegrationWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = -7390676268907876135L;

	private final String domain;

	private final String key;

	private final String authenticationType;

	public IntegrationWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		domain = request.getParameter("domain");
		key = request.getParameter("key");
		authenticationType = request.getParameter("authenticationType");
	}

	public String getDomain() {
		return domain;
	}

	public String getKey() {
		return key;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}
}