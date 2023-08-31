package com.dream.system.oauth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dream.system.convert.OAuthClientDetailsConvert;
import com.dream.system.mapper.OAuthClientDetailsMapper;
import com.dream.system.oauth.entity.OAuthClientDetails;
import com.dream.system.oauth.entity.OAuthClientDetailsVO;
import com.dream.system.oauth.service.OAuthService;
import com.wy.collection.ListHelper;

import dream.framework.web.helper.IpHelpers;
import lombok.extern.slf4j.Slf4j;

@Service("oauthService")
@Slf4j
public class OauthServiceImpl implements OAuthService {

	@Autowired
	private OAuthClientDetailsMapper oauthClientDetailsMapper;

	@Autowired
	private OAuthClientDetailsConvert oauthClientDetailsConvert;

	@Override
	@Transactional(readOnly = true)
	public OAuthClientDetails loadOauthClientDetails(String clientId) {
		List<OAuthClientDetails> oAuthClientDetails = oauthClientDetailsMapper
				.selectList(new LambdaQueryWrapper<OAuthClientDetails>().eq(OAuthClientDetails::getClientId, clientId));
		return ListHelper.isEmpty(oAuthClientDetails) ? null : oAuthClientDetails.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OAuthClientDetailsVO> loadAllOauthClientDetailsDtos() {
		List<OAuthClientDetails> clientDetailses = oauthClientDetailsMapper.selectList(
				new LambdaQueryWrapper<OAuthClientDetails>().orderByDesc(OAuthClientDetails::getCreateTime));
		return oauthClientDetailsConvert.convertt(clientDetailses);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void archiveOauthClientDetails(String clientId) {
		new LambdaUpdateWrapper<OAuthClientDetails>().set(OAuthClientDetails::isArchived, true)
				.eq(OAuthClientDetails::getClientId, clientId);
		log.debug("{}|Update OauthClientDetails(clientId: {}) archive = true", IpHelpers.getIp(), clientId);
	}

	@Override
	@Transactional(readOnly = true)
	public OAuthClientDetailsVO loadOauthClientDetailsDto(String clientId) {
		List<OAuthClientDetails> oAuthClientDetails = oauthClientDetailsMapper
				.selectList(new LambdaQueryWrapper<OAuthClientDetails>().eq(OAuthClientDetails::getClientId, clientId));
		return ListHelper.isEmpty(oAuthClientDetails) ? oauthClientDetailsConvert.convertt(oAuthClientDetails.get(0))
				: null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registerClientDetails(OAuthClientDetailsVO formDto) {
		OAuthClientDetails clientDetails = formDto.createDomain();
		oauthClientDetailsMapper.insert(clientDetails);
		log.debug("{}|Save OauthClientDetails: {}", IpHelpers.getIp(), clientDetails);
	}
}