package com.dream.web.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.electric.system.entity.SysUserEntity;

public interface SysUserDetailsService {

    /**
     * 获取 UserDetails 对象
     */
    UserDetails getUserDetails(SysUserEntity userEntity);
}