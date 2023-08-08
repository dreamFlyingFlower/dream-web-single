package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.enums.DataScopeEnum;
import com.dream.web.convert.RoleConvert;
import com.dream.web.entity.Role;
import com.dream.web.mapper.RoleMapper;
import com.dream.web.query.RoleQuery;
import com.dream.web.service.RoleDataScopeService;
import com.dream.web.service.RoleMenuService;
import com.dream.web.service.RoleService;
import com.dream.web.service.UserRoleService;
import com.dream.web.vo.RoleDataScopeVO;
import com.dream.web.vo.RoleVO;
import com.wy.lang.StrTool;
import com.wy.result.Result;

import lombok.AllArgsConstructor;

/**
 * 角色信息Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("roleService")
@AllArgsConstructor
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleVO, RoleQuery, RoleConvert, RoleMapper>
		implements RoleService {

	private final RoleMenuService sysRoleMenuService;

	private final RoleDataScopeService sysRoleDataScopeService;

	private final UserRoleService sysUserRoleService;

	public Result<RoleVO> page(RoleQuery query) {
		IPage<Role> page = baseMapper.selectPage(getPage(query), getWrapper(query));
		return new Result<>(baseConvert.convertt(page.getRecords()), page.getTotal());
	}

	public List<RoleVO> getList(RoleQuery query) {
		List<Role> entityList = baseMapper.selectList(getWrapper(query));
		return baseConvert.convertt(entityList);
	}

	private Wrapper<Role> getWrapper(RoleQuery query) {
		LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(StrTool.isNotBlank(query.getName()), Role::getName, query.getName());

		// 数据权限
		dataScopeWrapper(wrapper);

		return wrapper;
	}

	@Override
	public Role add(RoleVO vo) {
		Role entity = baseConvert.convert(vo);

		// 保存角色
		entity.setDataScope(DataScopeEnum.SELF.getValue());
		baseMapper.insert(entity);

		// 保存角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
		return entity;
	}

	@Override
	public Boolean edit(RoleVO vo) {
		Role entity = baseConvert.convert(vo);

		// 更新角色
		updateById(entity);

		// 更新角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void dataScope(RoleDataScopeVO vo) {
		Role entity = getById(vo.getId());
		entity.setDataScope(vo.getDataScope());
		// 更新角色
		updateById(entity);

		// 更新角色数据权限关系
		if (vo.getDataScope().equals(DataScopeEnum.CUSTOM.getValue())) {
			sysRoleDataScopeService.saveOrUpdate(entity.getId(), vo.getOrgIdList());
		} else {
			sysRoleDataScopeService.deleteByRoleIdList(Collections.singletonList(vo.getId()));
		}
	}

	@Override
	public Boolean deleteByIds(List<Serializable> idList) {
		// 删除角色
		removeByIds(idList);

		// 删除用户角色关系
		sysUserRoleService.deleteByRoleIdList(idList);

		// 删除角色菜单关系
		sysRoleMenuService.deleteByRoleIdList(idList);

		// 删除角色数据权限关系
		sysRoleDataScopeService.deleteByRoleIdList(idList);
		return true;
	}
}