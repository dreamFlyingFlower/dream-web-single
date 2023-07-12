package com.dream.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dream.basic.web.service.BaseService;
import com.dream.framework.web.query.MenuQuery;
import com.dream.framework.web.vo.MenuDTO;
import com.dream.framework.web.vo.UserVO;
import com.dream.web.entity.Menu;

/**
 * 菜单表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface MenuService extends BaseService<Menu, MenuDTO, MenuQuery> {

	Map<Long, MenuDTO> getCaches(List<Long> menuIds);

	List<MenuDTO> tree(Long id);

	List<MenuDTO> treeByRoleId(Long roleId);

	List<MenuDTO> treeByUseId(Long userId);

	Set<String> getUserAuthority(UserVO usesrVo);
}