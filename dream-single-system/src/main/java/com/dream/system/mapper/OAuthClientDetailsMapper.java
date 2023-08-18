package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.oauth.entity.OAuthClientDetails;
import com.dream.system.oauth.entity.OAuthClientDetailsVO;
import com.dream.system.query.OAuthClientDetailsQuery;

import dream.framework.web.mapper.BaseMappers;

/**
 * OAuth详情表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface OAuthClientDetailsMapper
		extends BaseMappers<OAuthClientDetails, OAuthClientDetailsVO, OAuthClientDetailsQuery> {

}