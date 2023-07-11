package com.dream.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dream.basic.core.helper.IpHelper;
import com.dream.web.convert.OAuthClientDetailsConvert;
import com.dream.web.entity.OAuthClientDetails;
import com.dream.web.entity.OAuthClientDetailsDTO;
import com.dream.web.repository.OauthRepository;
import com.dream.web.service.OauthService;

@Service("oauthService")
public class OauthServiceImpl implements OauthService {

	private static final Logger LOG = LoggerFactory.getLogger(OauthServiceImpl.class);

	@Autowired
	private OauthRepository oauthRepository;

	@Autowired
	private OAuthClientDetailsConvert oauthClientDetailsConvert;

	@Override
	@Transactional(readOnly = true)
	public OAuthClientDetails loadOauthClientDetails(String clientId) {
		return oauthRepository.findOauthClientDetails(clientId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OAuthClientDetailsDTO> loadAllOauthClientDetailsDtos() {
		List<OAuthClientDetails> clientDetailses = oauthRepository.findAllOauthClientDetails();
		return oauthClientDetailsConvert.convertt(clientDetailses);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void archiveOauthClientDetails(String clientId) {
		oauthRepository.updateOauthClientDetailsArchive(clientId, true);
		LOG.debug("{}|Update OauthClientDetails(clientId: {}) archive = true", IpHelper.getIp(), clientId);
	}

	@Override
	@Transactional(readOnly = true)
	public OAuthClientDetailsDTO loadOauthClientDetailsDto(String clientId) {
		final OAuthClientDetails oauthClientDetails = oauthRepository.findOauthClientDetails(clientId);
		return oauthClientDetails != null ? oauthClientDetailsConvert.convertt(oauthClientDetails) : null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registerClientDetails(OAuthClientDetailsDTO formDto) {
		OAuthClientDetails clientDetails = formDto.createDomain();
		oauthRepository.saveOauthClientDetails(clientDetails);
		LOG.debug("{}|Save OauthClientDetails: {}", IpHelper.getIp(), clientDetails);
	}
}