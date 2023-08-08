package com.dream.web.service.impl;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;

import com.electric.api.module.message.SmsApi;
import com.electric.framework.constant.Constant;
import com.electric.framework.exception.ServerException;
import com.electric.framework.security.cache.TokenStoreCache;
import com.electric.framework.security.mobile.MobileAuthenticationToken;
import com.electric.framework.security.user.UserDetail;
import com.electric.framework.security.utils.TokenUtils;
import com.electric.system.enums.LoginOperationEnum;
import com.electric.system.service.SysAuthService;
import com.electric.system.service.SysCaptchaService;
import com.electric.system.service.SysLogLoginService;
import com.electric.system.service.SysUserService;
import com.electric.system.vo.SysAccountLoginVO;
import com.electric.system.vo.SysMobileLoginVO;
import com.electric.system.vo.SysTokenVO;
import com.electric.system.vo.SysUserVO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 权限认证服务
 *
 * @author  
 */
@Service
@AllArgsConstructor
public class SysAuthServiceImpl implements AuthService {
    private final CaptchaService sysCaptchaService;
    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;
    private final LogLoginService sysLogLoginService;
    private final SysUserService sysUserService;
    private final SmsApi smsApi;
    
    @Override
    public TokenVO loginByAccount(SysAccountLoginVO login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 保存登录日志
            sysLogLoginService.save(login.getUsername(), Constant.FAIL, LoginOperationEnum.CAPTCHA_FAIL.getValue());

            throw new ServerException("验证码错误");
        }

        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new TokenVO(accessToken);
    }

    @Override
    public TokenVO loginByMobile(MobileLoginVO login) {
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new MobileAuthenticationToken(login.getMobile(), login.getCode()));
        } catch (BadCredentialsException e) {
            throw new ServerException("手机号或验证码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new TokenVO(accessToken);
    }

    @Override
    public boolean sendCode(String mobile) {
        // 生成6位验证码
        String code = RandomUtil.randomNumbers(6);

        SysUserVO user = sysUserService.getByMobile(mobile);
        if (user == null) {
            throw new ServerException("手机号未注册");
        }

        // 发送短信
        return smsApi.sendCode(mobile, "code", code);
    }

    @Override
    public void logout(String accessToken) {
        // 用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

        // 保存登录日志
        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGOUT_SUCCESS.getValue());
    }
}