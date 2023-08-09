package com.dream.web.oauth.entity;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wy.digest.DigestTool;
import com.wy.lang.StrTool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthClientDetailsVO implements Serializable {

	private static final long serialVersionUID = -6650829879035029696L;

	private String createTime;

	private boolean archived;

	@Builder.Default
	private String clientId = DigestTool.uuid();

	private String resourceIds;

	@Builder.Default
	private String clientSecret = DigestTool.uuid();

	private String scope;

	private String authorizedGrantTypes;

	private String webServerRedirectUri;

	private String authorities;

	private Integer accessTokenValidity;

	private Integer refreshTokenValidity;

	private String additionalInformation;

	private boolean trusted;

	public String getScopeWithBlank() {
		if (scope != null && scope.contains(",")) {
			return scope.replaceAll(",", " ");
		}
		return scope;
	}

	public boolean isContainsAuthorizationCode() {
		return this.authorizedGrantTypes.contains("authorization_code");
	}

	public boolean isContainsPassword() {
		return this.authorizedGrantTypes.contains("password");
	}

	public boolean isContainsImplicit() {
		return this.authorizedGrantTypes.contains("implicit");
	}

	public boolean isContainsClientCredentials() {
		return this.authorizedGrantTypes.contains("client_credentials");
	}

	public boolean isContainsRefreshToken() {
		return this.authorizedGrantTypes.contains("refresh_token");
	}

	public OAuthClientDetails createDomain() {
		OAuthClientDetails clientDetails = OAuthClientDetails.builder().clientId(clientId)
				.clientSecret(new BCryptPasswordEncoder().encode(clientSecret)).resourceIds(resourceIds)
				.authorizedGrantTypes(authorizedGrantTypes).scope(scope).build();

		if (StrTool.isNotBlank(webServerRedirectUri)) {
			clientDetails.setWebServerRedirectUri(webServerRedirectUri);
		}

		if (StrTool.isNotBlank(authorities)) {
			clientDetails.setAuthorities(authorities);
		}

		clientDetails.setAccessTokenValidity(accessTokenValidity);

		clientDetails.setRefreshTokenValidity(refreshTokenValidity);

		clientDetails.setTrusted(trusted);

		if (StrTool.isNotEmpty(additionalInformation)) {
			clientDetails.setAdditionalInformation(additionalInformation);
		}

		return clientDetails;
	}
}