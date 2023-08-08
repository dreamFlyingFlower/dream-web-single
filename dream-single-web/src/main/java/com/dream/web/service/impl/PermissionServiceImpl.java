package com.dream.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.PermissionConvert;
import com.dream.web.entity.Permission;
import com.dream.web.mapper.PermissionMapper;
import com.dream.web.query.PermissionQuery;
import com.dream.web.service.PermissionService;
import com.dream.web.vo.PermissionVO;

/**
 * 权限Service
 *
 * @author 飞花梦影
 * @date 2022-06-17 17:45:41
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Service("permissionService")
public class PermissionServiceImpl
		extends AbstractServiceImpl<Permission, PermissionVO, PermissionQuery, PermissionConvert, PermissionMapper>
		implements PermissionService {

	@Override
	public List<Permission> getPermissionsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}