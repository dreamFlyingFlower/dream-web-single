package com.dream.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dream.framework.web.helper.IpHelpers;
import lombok.extern.slf4j.Slf4j;

// @Controller
@Slf4j
@Deprecated
public class UaaController {

	@Autowired
	private AuthorizationServerTokenServices tokenService;

	@Autowired
	private AccessTokenConverter accessTokenConverter;

	@GetMapping(value = { "/login" })
	public String login(Model model) {
		log.info("Go to login, IP: {}", IpHelpers.getIp());
		return "login";
	}

	@GetMapping("/confirm_access")
	public String confirmAccess() {
		return "/oauth_approval";
	}

	@GetMapping("/oauth_error")
	public String oauthError() {
		return "/oauth_error";
	}

	@PostMapping("/oauth/check_token")
	@ResponseBody
	public Map<String, ?> checkToken(@RequestParam("token") String value) {
		DefaultTokenServices tokenServices = (DefaultTokenServices) tokenService;

		OAuth2AccessToken token = tokenServices.readAccessToken(value);
		if (token == null) {
			throw new InvalidTokenException("Token was not recognised");
		}

		if (token.isExpired()) {
			throw new InvalidTokenException("Token has expired");
		}
		OAuth2Authentication authentication = tokenServices.loadAuthentication(token.getValue());
		Map<String, ?> rst = accessTokenConverter.convertAccessToken(token, authentication);
		return rst;
	}
}