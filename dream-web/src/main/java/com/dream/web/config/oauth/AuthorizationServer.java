package com.dream.web.config.oauth;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.dream.web.entity.SelfJdbcClientDetailsService;
import com.dream.web.service.OauthService;

/**
 * OAuth2认证服务器配置
 *
 * @author 飞花梦影
 * @date 2022-06-17 16:42:13
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthorizationCodeServices authorizationCodeServices;

	// @Autowired
	// private AuthorizationServerTokenServices
	// defaulAuthorizationServerTokenServices;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private OauthService oauthService;

	@Autowired
	private TokenStore tokenStore;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ClientDetailsService clientDetailsService(DataSource dataSource) {
		SelfJdbcClientDetailsService clientDetailsService = new SelfJdbcClientDetailsService(dataSource);
		clientDetailsService.setPasswordEncoder(passwordEncoder());
		return clientDetailsService;
	}

	@Bean
	public AuthorizationServerTokenServices tokenService() {
		DefaultTokenServices service = new DefaultTokenServices();
		service.setClientDetailsService(clientDetailsService);
		service.setSupportRefreshToken(true);
		service.setTokenStore(tokenStore);

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter));
		service.setTokenEnhancer(tokenEnhancerChain);

		service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
		service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
		return service;
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	/**
	 * 配置客户端详细信息服务
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
	}

	@Bean
	public OAuth2RequestFactory oAuth2RequestFactory() {
		return new DefaultOAuth2RequestFactory(clientDetailsService);
	}

	@Bean
	public UserApprovalHandler userApprovalHandler() {
		OAuth2UserApprovalHandler userApprovalHandler = new OAuth2UserApprovalHandler();
		userApprovalHandler.setOauthService(oauthService);
		userApprovalHandler.setTokenStore(tokenStore);
		userApprovalHandler.setClientDetailsService(this.clientDetailsService);
		userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
		return userApprovalHandler;
	}

	/**
	 * 令牌访问端点
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager)
		        // .userDetailsService(userDetailsService)//
		        // 若无refresh_token会有UserDetailsService is required错误
		        .authorizationCodeServices(authorizationCodeServices).userApprovalHandler(userApprovalHandler())
		        // 使用默认的tokenService,默认实现 DefaultTokenServices
		        // .tokenServices(defaulAuthorizationServerTokenServices)
		        // 使用自定义的tokenService,改造后的 DefaultTokenServices
		        .tokenServices(tokenService()).pathMapping("/oauth/confirm_access", "/confirm_access")
		        .pathMapping("/oauth/error", "/oauth_error").allowedTokenEndpointRequestMethods(HttpMethod.POST)
		        .exceptionTranslator(new OAuth2WebResponseExceptionTranslator());
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new TokenEnhancer() {

			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				/*
				 * if (accessToken instanceof DefaultOAuth2AccessToken){ if(
				 * authentication.getPrincipal() instanceof UnifiedUserDetails){
				 * UnifiedUserDetails unifiedUserDetails =(UnifiedUserDetails)
				 * authentication.getPrincipal(); DefaultOAuth2AccessToken token=
				 * (DefaultOAuth2AccessToken) accessToken; Map<String, Object>
				 * additionalInformation = new LinkedHashMap<String, Object>();
				 * additionalInformation.put("mobile",unifiedUserDetails.getMobile());
				 * additionalInformation.put("tenant_id",unifiedUserDetails.getTenantId());
				 * additionalInformation.put("department_id",unifiedUserDetails.getDepartmentId(
				 * )); additionalInformation.put("user_authorities",unifiedUserDetails.
				 * getUserAuthorities()); token.setAdditionalInformation(additionalInformation);
				 * 
				 * }
				 * 
				 * }
				 */
				DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
				Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
				additionalInformation.put("code", 0);
				additionalInformation.put("msg", "success");
				token.setAdditionalInformation(additionalInformation);
				return accessToken;
			}
		};
	}

	/**
	 * 令牌访问端点安全策略
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()")
		        // 允许表单认证
		        .allowFormAuthenticationForClients();
	}
}