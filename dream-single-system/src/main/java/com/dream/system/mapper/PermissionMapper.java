package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.entity.PermissionEntity;
import com.dream.system.query.PermissionQuery;
import com.dream.system.vo.PermissionVO;

import dream.framework.web.mapper.BaseMappers;

/**
 * 权限
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface PermissionMapper extends BaseMappers<PermissionEntity, PermissionVO, PermissionQuery> {

}