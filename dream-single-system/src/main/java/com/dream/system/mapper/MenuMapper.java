package com.dream.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.entity.MenuEntity;
import com.dream.system.query.MenuQuery;
import com.dream.system.vo.MenuVO;

/**
 * 菜单表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface MenuMapper extends BaseMappers<MenuEntity, MenuVO, MenuQuery> {

	/**
	 * 查询所有权限列表
	 */
	List<String> getAuthorityList();

	/**
	 * 查询用户权限列表
	 * 
	 * @param userId 用户ID
	 */
	List<String> getUserAuthorityList(@Param("userId") Long userId);

	/**
	 * 查询所有菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<MenuEntity> getMenuList(@Param("type") Integer type);

	/**
	 * 查询用户菜单列表
	 *
	 * @param userId 用户ID
	 * @param type 菜单类型
	 */
	List<MenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("type") Integer type);
}