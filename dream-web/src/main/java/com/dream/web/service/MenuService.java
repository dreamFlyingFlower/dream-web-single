package com.dream.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dream.basic.web.service.BaseService;
import com.dream.framework.web.query.MenuQuery;
import com.dream.framework.web.vo.MenuVO;
import com.dream.framework.web.vo.UserVO;
import com.dream.web.entity.Menu;

/**
 * 菜单表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface MenuService extends BaseService<Menu, MenuVO, MenuQuery> {

	Map<Long, MenuVO> getCaches(List<Long> menuIds);

	List<MenuVO> tree(Long id);

	List<MenuVO> treeByRoleId(Long roleId);

	List<MenuVO> treeByUseId(Long userId);

	Set<String> getUserAuthority(UserVO usesrVo);
}