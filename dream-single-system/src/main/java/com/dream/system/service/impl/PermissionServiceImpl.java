package com.dream.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.convert.PermissionConvert;
import com.dream.system.entity.PermissionEntity;
import com.dream.system.mapper.PermissionMapper;
import com.dream.system.query.PermissionQuery;
import com.dream.system.service.PermissionService;
import com.dream.system.vo.PermissionVO;

/**
 * 权限Service
 *
 * @author 飞花梦影
 * @date 2022-06-17 17:45:41
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Service("permissionService")
public class PermissionServiceImpl
		extends AbstractServiceImpl<PermissionEntity, PermissionVO, PermissionQuery, PermissionConvert, PermissionMapper>
		implements PermissionService {

	@Override
	public List<PermissionEntity> getPermissionsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}