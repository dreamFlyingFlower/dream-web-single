package com.dream.web.entity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import com.dream.basic.core.helper.SpringContextHelper;
import com.dream.web.agent.AccountApiAgent;
import com.dream.web.vo.AccountDTO;
import com.dream.web.vo.LoginAccountVO;
import com.wy.lang.StrTool;
import com.wy.result.Result;

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

		// 1.从客户端取数据
		String username = token.getName();
		if (StrTool.isBlank(username)) {
			throw new BadCredentialsException("账户为空");
		}
		if (token.getCredentials() == null) {
			throw new BadCredentialsException("密码为空");
		}
		String presentedPassword = token.getCredentials().toString();

		// 2.远程调用统一账户服务,进行账户密码校验
		LoginAccountVO loginAccountVO = new LoginAccountVO();
		loginAccountVO.setDomain(domain);
		loginAccountVO.setUsername(username);
		loginAccountVO.setMobile(username);
		loginAccountVO.setPassword(presentedPassword);
		AccountApiAgent accountApiAgent = SpringContextHelper.getBean(AccountApiAgent.class);
		Result<AccountDTO> restResponse = accountApiAgent.login(loginAccountVO);

		// 3.异常处理
		if (restResponse.getCode() != 0) {
			throw new BadCredentialsException("登录失败");
		}

		// 4.登录成功,把用户数据封装到UnifiedUserDetails对象中
		UnifiedUserDetails unifiedUserDetails = new UnifiedUserDetails(restResponse.getData().getUsername(),
				presentedPassword, AuthorityUtils.createAuthorityList());
		unifiedUserDetails.setMobile(restResponse.getData().getMobile());
		return unifiedUserDetails;
	}
}