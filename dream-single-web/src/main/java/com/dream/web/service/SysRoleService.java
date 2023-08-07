package com.dream.web.service;

import com.electric.framework.page.PageResult;
import com.electric.framework.service.BaseService;
import com.electric.system.entity.SysRoleEntity;
import com.electric.system.query.SysRoleQuery;
import com.electric.system.vo.SysRoleDataScopeVO;
import com.electric.system.vo.SysRoleVO;

import java.util.List;

/**
 * 角色
 * 
 * @author  
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRoleVO vo);

	void update(SysRoleVO vo);

	void dataScope(SysRoleDataScopeVO vo);

	void delete(List<Long> idList);
}