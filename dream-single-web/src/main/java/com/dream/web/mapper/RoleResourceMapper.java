package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.RoleResourceEntity;
import com.dream.web.query.RoleResourceQuery;
import com.dream.web.vo.RoleResourceVO;

/**
 * 角色-权限关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleResourceMapper extends BaseMappers<RoleResourceEntity, RoleResourceVO, RoleResourceQuery> {

}