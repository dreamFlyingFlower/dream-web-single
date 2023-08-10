package com.dream.system.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.dream.system.entity.OperateLog;
import com.dream.system.service.OperateLogService;
import com.dream.system.vo.UserVO;
import com.wy.enums.TipEnum;
import com.wy.logger.BusinessType;

/**
 * 认证事件处理
 *
 * @author
 */
@Component
public class AuthenticationEvents {

	@Autowired
	private OperateLogService operateLogService;

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent event) {
		UserVO user = (UserVO) event.getAuthentication().getPrincipal();
		// 保存登录日志
		operateLogService.saveOperateLog(OperateLog.builder().operator(user.getUsername())
				.status(TipEnum.TIP_REQUEST_SUCCESS.getCode()).businessType(BusinessType.LOGIN.getCode()).build());
	}

	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent event) {
		// 用户名
		String username = (String) event.getAuthentication().getPrincipal();
		// 保存登录日志
		operateLogService.saveOperateLog(OperateLog.builder().operator(username)
				.status(TipEnum.TIP_REQUEST_SUCCESS.getCode()).businessType(BusinessType.LOGIN.getCode()).build());
	}
}