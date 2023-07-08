package com.dream.web.repository;

import java.util.List;

import com.dream.web.entity.OAuthClientDetails;

public interface OauthRepository {

	OAuthClientDetails findOauthClientDetails(String clientId);

	List<OAuthClientDetails> findAllOauthClientDetails();

	void updateOauthClientDetailsArchive(String clientId, boolean archive);

	void saveOauthClientDetails(OAuthClientDetails clientDetails);

}