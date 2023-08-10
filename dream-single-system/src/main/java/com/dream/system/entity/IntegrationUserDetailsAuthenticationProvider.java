package com.dream.system.entity;

import java.util.Map;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wy.lang.StrTool;

/**
 * 统一用户认证处理,集成了网页(简化模式、授权码模式用户登录)认证 与 password模式认证 FIXME
 * 
 * @author 飞花梦影
 * @date 2023-08-09 23:26:47
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Deprecated
public class IntegrationUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private IntegrationUserDetailsAuthenticationHandler authenticationHandler = null;

	public IntegrationUserDetailsAuthenticationProvider(
			IntegrationUserDetailsAuthenticationHandler authenticationHandler) {
		this.authenticationHandler = authenticationHandler;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// 仅在父类中验证用户的状态
	}

	@Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		try {

			UserDetails loadedUser = authenticationUser(authentication);
			if (loadedUser == null) {
				throw new InternalAuthenticationServiceException(
						"UserDetailsService returned null, which is an interface contract violation");
			}
			return loadedUser;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (InternalAuthenticationServiceException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}

	private UserDetails authenticationUser(UsernamePasswordAuthenticationToken authentication) {

		Object details = authentication.getDetails();
		String domain = null;
		String authenticationType = null;
		if (details instanceof IntegrationWebAuthenticationDetails) {
			// 网页(简化模式、授权码模式用户登录)认证
			IntegrationWebAuthenticationDetails webAuthenticationDetails =
					(IntegrationWebAuthenticationDetails) details;
			domain = webAuthenticationDetails.getDomain();
			authenticationType = webAuthenticationDetails.getAuthenticationType();
		} else if (details instanceof Map) {
			// password模式认证
			@SuppressWarnings("unchecked")
			Map<String, String> webAuthenticationDetails = (Map<String, String>) details;
			domain = webAuthenticationDetails.get("domain");
			authenticationType = webAuthenticationDetails.get("authenticationType");
		} else {
			// 超出预估的情况
			throw new InternalAuthenticationServiceException("WebAuthenticationDetails type is not support");
		}

		if (StrTool.isBlank(domain)) {
			throw new InternalAuthenticationServiceException("domain is blank");
		}

		if (StrTool.isBlank(authenticationType)) {
			throw new InternalAuthenticationServiceException("authenticationType is blank");
		}
		return authenticationHandler.authentication(domain, authenticationType, authentication);
	}
}