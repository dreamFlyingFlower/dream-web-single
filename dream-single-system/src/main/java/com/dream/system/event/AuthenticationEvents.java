package com.dream.system.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.dream.framework.enums.LoginOperationEnum;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.service.LogLoginService;
import com.wy.enums.TipEnum;

/**
 * 认证事件处理
 *
 * @author 飞花梦影
 * @date 2023-08-14 17:41:37
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Component
public class AuthenticationEvents {

	@Autowired
	private LogLoginService logLoginService;

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent event) {
		SecurityUserDetails user = (SecurityUserDetails) event.getAuthentication().getPrincipal();
		// 保存登录日志
		logLoginService.save(user.getUsername(), TipEnum.TIP_REQUEST_SUCCESS.getCode(),
				LoginOperationEnum.LOGIN_SUCCESS.getCode());
	}

	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent event) {
		// 用户名
		String username = (String) event.getAuthentication().getPrincipal();
		// 保存登录日志
		logLoginService.save(username, TipEnum.TIP_REQUEST_SUCCESS.getCode(), LoginOperationEnum.LOGIN_FAIL.getCode());
	}
}