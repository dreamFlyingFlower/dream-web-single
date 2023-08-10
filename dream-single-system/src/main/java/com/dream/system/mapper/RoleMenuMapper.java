package com.dream.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.entity.RoleMenuEntity;
import com.dream.system.query.RoleMenuQuery;
import com.dream.system.vo.RoleMenuVO;

/**
 * 角色与菜单对应关系
 *
 * @author 飞花梦影
 * @date 2023-08-08 10:30:59
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface RoleMenuMapper extends BaseMappers<RoleMenuEntity, RoleMenuVO, RoleMenuQuery> {

	/**
	 * 根据角色ID,获取菜单ID列表
	 */
	List<Long> getMenuIdList(@Param("roleId") Long roleId);
}