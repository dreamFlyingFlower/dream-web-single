package com.dream.system.service.impl;

import org.springframework.stereotype.Service;

import com.dream.system.convert.RoleResourceConvert;
import com.dream.system.entity.RoleResourceEntity;
import com.dream.system.mapper.RoleResourceMapper;
import com.dream.system.query.RoleResourceQuery;
import com.dream.system.service.RoleResourceService;
import com.dream.system.vo.RoleResourceVO;

import dream.framework.web.service.impl.AbstractServiceImpl;

/**
 * 角色-资源关系Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl extends
        AbstractServiceImpl<RoleResourceEntity, RoleResourceVO, RoleResourceQuery, RoleResourceConvert, RoleResourceMapper>
        implements RoleResourceService {

}