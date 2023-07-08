package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.RoleResourceConvert;
import com.dream.web.entity.RoleResource;
import com.dream.web.mapper.RoleResourceMapper;
import com.dream.web.query.RoleResourceQuery;
import com.dream.web.service.RoleResourceService;
import com.dream.web.vo.RoleResourceDTO;

/**
 * 角色-资源关系Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl extends
        AbstractServiceImpl<RoleResource, RoleResourceDTO, RoleResourceQuery, RoleResourceConvert, RoleResourceMapper>
        implements RoleResourceService {

}