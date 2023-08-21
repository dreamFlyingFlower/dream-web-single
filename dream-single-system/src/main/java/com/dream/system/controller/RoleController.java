package com.dream.system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.convert.RoleConvert;
import com.dream.system.entity.RoleEntity;
import com.dream.system.query.RoleQuery;
import com.dream.system.query.UserRoleQuery;
import com.dream.system.service.DataScopeService;
import com.dream.system.service.MenuService;
import com.dream.system.service.RoleMenuService;
import com.dream.system.service.RoleService;
import com.dream.system.service.UserRoleService;
import com.dream.system.service.UserService;
import com.dream.system.vo.DataScopeVO;
import com.dream.system.vo.MenuVO;
import com.dream.system.vo.RoleVO;
import com.dream.system.vo.UserVO;
import com.wy.result.Result;

import dream.framework.mybatis.plus.controller.MyBatisPlusController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 角色管理API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "角色管理API")
@RestController
@RequestMapping("sys/role")
@AllArgsConstructor
public class RoleController extends MyBatisPlusController<RoleEntity, RoleVO, RoleQuery, RoleService> {

	private final UserService userService;

	private final RoleMenuService roleMenuService;

	private final DataScopeService roleDataScopeService;

	private final MenuService menuService;

	private final UserRoleService userRoleService;

	@GetMapping("{id}")
	@Operation(summary = "信息")
	public Result<RoleVO> get(@PathVariable("id") Long id) {
		RoleEntity entity = baseService.getById(id);
		RoleVO role = RoleConvert.INSTANCE.convertt(entity);

		// 查询角色对应的菜单
		List<Long> menuIdList = roleMenuService.getMenuIdList(id);
		role.setMenuIdList(menuIdList);

		// 查询角色对应的数据权限
		List<Long> orgIdList = roleDataScopeService.getOrgIdList(id);
		role.setOrgIdList(orgIdList);

		return Result.ok(role);
	}

	@PutMapping("data-scope")
	@Operation(summary = "数据权限")
	public Result<String> dataScope(@RequestBody @Valid DataScopeVO vo) {
		baseService.dataScope(vo);
		return Result.ok();
	}

	@GetMapping("menu")
	@Operation(summary = "角色菜单")
	public Result<List<MenuVO>> menu() {
		SecurityUserDetails user = SecurityHelper.getUser();
		List<MenuVO> list = menuService.getUserMenuList(user, null);
		return Result.ok(list);
	}

	@GetMapping("user/page")
	@Operation(summary = "角色用户-分页")
	public Result<List<UserVO>> userPage(@Valid UserRoleQuery query) {
		return userService.roleUserPage(query);
	}

	@DeleteMapping("user/{roleId}")
	@Operation(summary = "删除角色用户")
	public Result<String> userDelete(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
		userRoleService.deleteByUserIdList(roleId, userIdList);
		return Result.ok();
	}

	@PostMapping("user/{roleId}")
	@Operation(summary = "分配角色给用户列表")
	public Result<String> userSave(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
		userRoleService.saveUserList(roleId, userIdList);
		return Result.ok();
	}
}