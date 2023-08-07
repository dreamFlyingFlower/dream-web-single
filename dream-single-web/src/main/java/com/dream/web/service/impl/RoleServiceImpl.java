package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.RoleQuery;
import com.dream.system.web.vo.RoleVO;
import com.dream.web.convert.RoleConvert;
import com.dream.web.entity.Role;
import com.dream.web.mapper.RoleMapper;
import com.dream.web.service.RoleService;

/**
 * 角色信息Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleVO, RoleQuery, RoleConvert, RoleMapper> implements RoleService {

}