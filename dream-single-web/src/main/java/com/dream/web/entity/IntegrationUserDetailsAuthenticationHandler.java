package com.dream.web.entity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import com.dream.basic.core.helper.SpringContextHelper;
import com.dream.web.service.AuthService;
import com.dream.web.vo.AccountVO;
import com.dream.web.vo.LoginAccountVO;
import com.wy.lang.StrTool;

public class IntegrationUserDetailsAuthenticationHandler {

	/**
	 * 认证处理
	 * 
	 * @param domain 用户域 ,如b端用户、c端用户等
	 * @param authenticationType 认证类型,如密码认证,短信认证等
	 * @param token
	 * @return
	 */
	public UnifiedUserDetails authentication(String domain, String authenticationType,
			UsernamePasswordAuthenticationToken token) {

		// 从客户端取数据
		String username = token.getName();
		if (StrTool.isBlank(username)) {
			throw new BadCredentialsException("账户为空");
		}
		if (token.getCredentials() == null) {
			throw new BadCredentialsException("密码为空");
		}
		String presentedPassword = token.getCredentials().toString();

		// 远程调用统一账户服务,进行账户密码校验
		LoginAccountVO loginAccountVO = new LoginAccountVO();
		loginAccountVO.setDomain(domain);
		loginAccountVO.setUsername(username);
		loginAccountVO.setMobile(username);
		loginAccountVO.setPassword(presentedPassword);
		AuthService authService = SpringContextHelper.getBean(AuthService.class);
		// FIXME 登录从那来?
		// AccountVO accountVO = authService.loginByAccount(loginAccountVO);
		AccountVO accountVO = new AccountVO();

		if (null == accountVO) {
			throw new BadCredentialsException("登录失败");
		}

		UnifiedUserDetails unifiedUserDetails = new UnifiedUserDetails(accountVO.getUsername(), presentedPassword,
				AuthorityUtils.createAuthorityList());
		unifiedUserDetails.setMobile(accountVO.getMobile());
		return unifiedUserDetails;
	}
}