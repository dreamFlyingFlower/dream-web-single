package com.dream.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import com.electric.framework.constant.Constant;
import com.electric.framework.utils.Result;
import com.electric.system.convert.SysOrgConvert;
import com.electric.system.entity.SysOrgEntity;
import com.electric.system.service.SysOrgService;
import com.electric.system.vo.SysOrgVO;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 机构管理
 * 
 * @author  
 */
@RestController
@RequestMapping("sys/org")
@Tag(name="机构管理")
@AllArgsConstructor
public class SysOrgController {
	private final SysOrgService sysOrgService;

	@GetMapping("list")
	@Operation(summary = "列表")
	@PreAuthorize("hasAuthority('sys:org:list')")
	public Result<List<SysOrgVO>> list(){
		List<SysOrgVO> list = sysOrgService.getList();

		return Result.ok(list);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	@PreAuthorize("hasAuthority('sys:org:info')")
	public Result<SysOrgVO> get(@PathVariable("id") Long id){
		SysOrgEntity entity = sysOrgService.getById(id);
		SysOrgVO vo =SysOrgConvert.INSTANCE.convert(entity);

		// 获取上级机构名称
		if(!Constant.ROOT.equals(entity.getPid())){
			SysOrgEntity parentEntity = sysOrgService.getById(entity.getPid());
			vo.setParentName(parentEntity.getName());
		}

		return Result.ok(vo);
	}

	@PostMapping
	@Operation(summary = "保存")
	@PreAuthorize("hasAuthority('sys:org:save')")
	public Result<String> save(@RequestBody @Valid SysOrgVO vo){
		sysOrgService.save(vo);

		return Result.ok();
	}

	@PutMapping
	@Operation(summary = "修改")
	@PreAuthorize("hasAuthority('sys:org:update')")
	public Result<String> update(@RequestBody @Valid SysOrgVO vo){
		sysOrgService.update(vo);

		return Result.ok();
	}

	@DeleteMapping
	@Operation(summary = "删除")
	@PreAuthorize("hasAuthority('sys:org:delete')")
	public Result<String> delete(@RequestBody List<Long> idList){
		sysOrgService.delete(idList.get(0));

		return Result.ok();
	}
	
}