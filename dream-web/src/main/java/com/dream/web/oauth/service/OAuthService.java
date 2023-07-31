package com.dream.web.oauth.service;

import java.util.List;

import com.dream.web.entity.OAuthClientDetails;
import com.dream.web.entity.OAuthClientDetailsDTO;

public interface OAuthService {

	OAuthClientDetails loadOauthClientDetails(String clientId);

	List<OAuthClientDetailsDTO> loadAllOauthClientDetailsDtos();

	void archiveOauthClientDetails(String clientId);

	OAuthClientDetailsDTO loadOauthClientDetailsDto(String clientId);

	void registerClientDetails(OAuthClientDetailsDTO formDto);
}