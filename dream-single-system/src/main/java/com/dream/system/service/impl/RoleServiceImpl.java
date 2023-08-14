package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dream.framework.enums.DataScopeEnum;
import com.dream.system.convert.RoleConvert;
import com.dream.system.entity.RoleEntity;
import com.dream.system.mapper.RoleMapper;
import com.dream.system.query.RoleQuery;
import com.dream.system.service.DataScopeService;
import com.dream.system.service.RoleMenuService;
import com.dream.system.service.RoleService;
import com.dream.system.service.UserRoleService;
import com.dream.system.vo.DataScopeVO;
import com.dream.system.vo.RoleVO;
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
public class RoleServiceImpl extends AbstractScopeServiceImpl<RoleEntity, RoleVO, RoleQuery, RoleConvert, RoleMapper>
		implements RoleService {

	private final RoleMenuService sysRoleMenuService;

	private final DataScopeService sysRoleDataScopeService;

	private final UserRoleService sysUserRoleService;

	@Override
	public Result<List<RoleVO>> page(RoleQuery query) {
		IPage<RoleEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
		return Result.page(baseConvert.convertt(page.getRecords()), page.getCurrent(), page.getSize(), page.getTotal());
	}

	@Override
	public Result<List<RoleVO>> list(RoleQuery query) {
		List<RoleEntity> entityList = baseMapper.selectList(getWrapper(query));
		return Result.ok(baseConvert.convertt(entityList));
	}

	private Wrapper<RoleEntity> getWrapper(RoleQuery query) {
		LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(StrTool.isNotBlank(query.getRoleName()), RoleEntity::getRoleName, query.getRoleName());

		// 数据权限
		dataScopeWrapper(wrapper);

		return wrapper;
	}

	@Override
	public RoleEntity add(RoleVO vo) {
		RoleEntity entity = baseConvert.convert(vo);

		// 保存角色
		entity.setDataScope(DataScopeEnum.SELF.getCode());
		baseMapper.insert(entity);

		// 保存角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
		return entity;
	}

	@Override
	public Boolean edit(RoleVO vo) {
		RoleEntity entity = baseConvert.convert(vo);

		// 更新角色
		updateById(entity);

		// 更新角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getId(), vo.getMenuIdList());
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void dataScope(DataScopeVO vo) {
		RoleEntity entity = getById(vo.getId());
		entity.setDataScope(vo.getDataScope());
		// 更新角色
		updateById(entity);

		// 更新角色数据权限关系
		if (vo.getDataScope().equals(DataScopeEnum.CUSTOM.getCode())) {
			sysRoleDataScopeService.saveOrUpdate(entity.getId(), vo.getOrgIdList());
		} else {
			sysRoleDataScopeService.deleteByRoleIdList(Collections.singletonList(vo.getId()));
		}
	}

	@Override
	public Boolean deletes(List<Serializable> idList) {
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