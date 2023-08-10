package com.dream.system.service;

import com.dream.system.vo.LoginAccountVO;
import com.dream.system.vo.MobileLoginVO;
import com.dream.system.vo.TokenVO;

/**
 * 权限认证服务
 *
 * @author 飞花梦影
 * @date 2023-08-08 13:35:06
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface AuthService {

	/**
	 * 账号密码登录
	 *
	 * @param login 登录信息
	 */
	TokenVO loginByAccount(LoginAccountVO login);

	/**
	 * 手机短信登录
	 *
	 * @param login 登录信息
	 */
	TokenVO loginByMobile(MobileLoginVO login);

	/**
	 * 发送手机验证码
	 *
	 * @param mobile 手机号
	 */
	boolean sendCode(String mobile);

	/**
	 * 退出登录
	 *
	 * @param accessToken accessToken
	 */
	void logout(String accessToken);
}