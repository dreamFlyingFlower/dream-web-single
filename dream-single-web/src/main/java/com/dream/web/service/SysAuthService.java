package com.dream.web.service;

import com.electric.system.vo.SysAccountLoginVO;
import com.electric.system.vo.SysMobileLoginVO;
import com.electric.system.vo.SysTokenVO;

/**
 * 权限认证服务
 *
 * @author  
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByAccount(SysAccountLoginVO login);

    /**
     * 手机短信登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByMobile(SysMobileLoginVO login);

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