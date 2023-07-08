package com.dream.web.entity;

import java.io.Serializable;

import com.dream.web.repository.PasswordHandler;
import com.dream.web.utils.GuidGenerator;
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
public class OAuthClientDetailsDTO implements Serializable {

	private static final long serialVersionUID = -6650829879035029696L;

	private String createTime;

	private boolean archived;

	@Builder.Default
	private String clientId = DigestTool.uuid();

	private String resourceIds;

	@Builder.Default
	private String clientSecret = GuidGenerator.generateClientSecret();

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
		OAuthClientDetails clientDetails = new OAuthClientDetails().clientId(clientId)
				// encrypted client secret
				.clientSecret(PasswordHandler.encode(clientSecret)).resourceIds(resourceIds)
				.authorizedGrantTypes(authorizedGrantTypes).scope(scope);

		if (StrTool.isNotBlank(webServerRedirectUri)) {
			clientDetails.webServerRedirectUri(webServerRedirectUri);
		}

		if (StrTool.isNotBlank(authorities)) {
			clientDetails.authorities(authorities);
		}

		clientDetails.accessTokenValidity(accessTokenValidity).refreshTokenValidity(refreshTokenValidity)
				.trusted(trusted);

		if (StrTool.isNotEmpty(additionalInformation)) {
			clientDetails.additionalInformation(additionalInformation);
		}

		return clientDetails;
	}
}