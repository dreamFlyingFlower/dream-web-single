package com.dream.web.config.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

import com.dream.web.entity.OAuthClientDetails;
import com.dream.web.service.OauthService;

/**
 * 自定义用户token通过处理器
 * 
 * @author 飞花梦影
 * @date 2022-09-13 17:47:59
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public class OAuth2UserApprovalHandler extends TokenStoreUserApprovalHandler {

	private OauthService oauthService;

	public OAuth2UserApprovalHandler() {}

	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if (super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}
		if (!userAuthentication.isAuthenticated()) {
			return false;
		}

		OAuthClientDetails clientDetails = oauthService.loadOauthClientDetails(authorizationRequest.getClientId());
		return clientDetails != null && clientDetails.trusted();
	}

	public void setOauthService(OauthService oauthService) {
		this.oauthService = oauthService;
	}
}