package com.dream.system.oauth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

import com.dream.system.oauth.entity.OAuthClientDetails;
import com.dream.system.oauth.service.OAuthService;

/**
 * 自定义用户token通过处理器
 * 
 * @author 飞花梦影
 * @date 2022-09-13 17:47:59
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Deprecated
public class OAuth2UserApprovalHandler extends TokenStoreUserApprovalHandler {

	private OAuthService oauthService;

	public OAuth2UserApprovalHandler() {
	}

	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if (super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}
		if (!userAuthentication.isAuthenticated()) {
			return false;
		}

		OAuthClientDetails clientDetails = oauthService.loadOauthClientDetails(authorizationRequest.getClientId());
		return clientDetails != null && clientDetails.isTrusted();
	}

	public void setOauthService(OAuthService oauthService) {
		this.oauthService = oauthService;
	}
}