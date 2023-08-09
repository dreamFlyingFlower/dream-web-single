package com.dream.web.oauth.service;

import java.util.List;

import com.dream.web.oauth.entity.OAuthClientDetails;
import com.dream.web.oauth.entity.OAuthClientDetailsVO;

public interface OAuthService {

	OAuthClientDetails loadOauthClientDetails(String clientId);

	List<OAuthClientDetailsVO> loadAllOauthClientDetailsDtos();

	void archiveOauthClientDetails(String clientId);

	OAuthClientDetailsVO loadOauthClientDetailsDto(String clientId);

	void registerClientDetails(OAuthClientDetailsVO formDto);
}