package com.dream.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.Menu;
import com.dream.web.query.MenuQuery;

/**
 * 菜单表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface MenuMapper extends BaseMappers<Menu, MenuQuery> {

	// List<Menu> list(Page<Menu> page, @Param("query") MenuQuery query);

	// List<Menu> list(@Param("query") MenuQuery query);
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
}