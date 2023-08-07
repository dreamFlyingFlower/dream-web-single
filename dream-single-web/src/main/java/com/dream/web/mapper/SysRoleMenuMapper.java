package com.dream.web.mapper;

import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysRoleMenuEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author  
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMappers<SysRoleMenuEntity> {

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> getMenuIdList(@Param("roleId") Long roleId);
}