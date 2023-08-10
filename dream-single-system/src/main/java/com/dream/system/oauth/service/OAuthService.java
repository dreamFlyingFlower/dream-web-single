package com.dream.system.oauth.service;

import java.util.List;

import com.dream.system.oauth.entity.OAuthClientDetails;
import com.dream.system.oauth.entity.OAuthClientDetailsVO;

public interface OAuthService {

	OAuthClientDetails loadOauthClientDetails(String clientId);

	List<OAuthClientDetailsVO> loadAllOauthClientDetailsDtos();

	void archiveOauthClientDetails(String clientId);

	OAuthClientDetailsVO loadOauthClientDetailsDto(String clientId);

	void registerClientDetails(OAuthClientDetailsVO formDto);
}