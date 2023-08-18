package com.dream.system.controller;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.framework.enums.MenuType;
import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.convert.MenuConvert;
import com.dream.system.entity.MenuEntity;
import com.dream.system.query.MenuQuery;
import com.dream.system.service.MenuService;
import com.dream.system.vo.MenuVO;
import com.wy.result.Result;

import dream.framework.core.constant.ConstCore;
import dream.framework.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 菜单管理API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "菜单管理API")
@RestController
@RequestMapping("sys/menu")
public class MenuController extends AbstractController<MenuEntity, MenuVO, MenuQuery, MenuService> {

	@GetMapping("nav")
	@Operation(summary = "菜单导航")
	public Result<List<MenuVO>> nav() {
		SecurityUserDetails user = SecurityHelper.getUser();
		List<MenuVO> list = baseService.getUserMenuList(user, MenuType.MENU.getCode());
		return Result.ok(list);
	}

	@GetMapping("authority")
	@Operation(summary = "用户权限标识")
	public Result<Set<String>> authority() {
		SecurityUserDetails user = SecurityHelper.getUser();
		Set<String> set = baseService.getUserAuthority(user);
		return Result.ok(set);
	}

	@GetMapping("list")
	@Operation(summary = "菜单列表")
	@Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  2：接口  null：全部")
	@PreAuthorize("hasAuthority('sys:menu:list')")
	public Result<List<MenuVO>> list(Integer type) {
		List<MenuVO> list = baseService.getMenuList(type);

		return Result.ok(list);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	@PreAuthorize("hasAuthority('sys:menu:info')")
	public Result<MenuVO> get(@PathVariable("id") Long id) {
		MenuEntity entity = baseService.getById(id);
		MenuVO vo = MenuConvert.INSTANCE.convertt(entity);

		// 获取上级菜单名称
		if (!ConstCore.ROOT.equals(entity.getPid())) {
			MenuEntity parentEntity = baseService.getById(entity.getPid());
			vo.setParentName(parentEntity.getMenuName());
		}

		return Result.ok(vo);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "删除")
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	public Result<String> delete(@PathVariable("id") Long id) {
		// 判断是否有子菜单或按钮
		Long count = baseService.getSubMenuCount(id);
		if (count > 0) {
			return Result.error("请先删除子菜单");
		}

		baseService.delete(id);

		return Result.ok();
	}
}