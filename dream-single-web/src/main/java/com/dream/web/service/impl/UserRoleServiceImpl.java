package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.UserRoleQuery;
import com.dream.system.web.vo.UserRoleVO;
import com.dream.web.convert.UserRoleConvert;
import com.dream.web.entity.UserRole;
import com.dream.web.mapper.UserRoleMapper;
import com.dream.web.service.UserRoleService;

/**
 * 账号-角色关系Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRole, UserRoleVO, UserRoleQuery, UserRoleConvert, UserRoleMapper> implements UserRoleService {

}