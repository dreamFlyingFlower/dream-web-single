package com.dream.web.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.dream.web.entity.UnifiedUserAuthenticationConverter;
import com.dream.web.oauth.config.SelfAccessTokenConverter;

/**
 * Token配置
 *
 * @author 飞花梦影
 * @date 2022-06-17 16:41:06
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Deprecated
public class TokenConfig {

	/**
	 * 秘钥串
	 */
	private static final String SIGNING_KEY = "dream";

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// 对称秘钥,资源服务器使用该秘钥来解密
		converter.setSigningKey(SIGNING_KEY);
		SelfAccessTokenConverter accessTokenConverter = new SelfAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(new UnifiedUserAuthenticationConverter());
		converter.setAccessTokenConverter(accessTokenConverter);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * 配置令牌管理
	 */
	@Bean
	public AuthorizationServerTokenServices tokenService(ClientDetailsService clientDetailsService,
			TokenStore tokenStore, JwtAccessTokenConverter accessTokenConverter) {
		DefaultTokenServices service = new DefaultTokenServices();
		service.setClientDetailsService(clientDetailsService);
		service.setSupportRefreshToken(true);
		service.setTokenStore(tokenStore);
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
		service.setTokenEnhancer(tokenEnhancerChain);
		return service;
	}

	/**
	 * 授权码存储方式
	 */
	@Bean
	public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
		return new JdbcAuthorizationCodeServices(dataSource);
	}
}