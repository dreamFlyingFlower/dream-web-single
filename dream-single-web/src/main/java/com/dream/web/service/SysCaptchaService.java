package com.dream.web.service;

import com.electric.system.vo.SysCaptchaVO;

/**
 * 验证码
 *
 * @author  
 */
public interface SysCaptchaService {
    /**
     * 生成验证码
     */
    SysCaptchaVO generate();

    /**
     * 验证码效验
     *
     * @param key  key
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String key, String code);
}