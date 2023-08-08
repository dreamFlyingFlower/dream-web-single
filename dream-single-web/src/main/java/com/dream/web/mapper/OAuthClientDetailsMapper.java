package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.OAuthClientDetails;
import com.dream.web.query.OAuthClientDetailsQuery;

/**
 * OAuth详情表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface OAuthClientDetailsMapper extends BaseMappers<OAuthClientDetails, OAuthClientDetailsQuery> {

}