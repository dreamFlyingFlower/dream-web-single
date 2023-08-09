package com.dream.web.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.DictEntity;
import com.dream.web.query.DictQuery;
import com.dream.web.service.DictService;
import com.dream.web.vo.DictItemVO;
import com.dream.web.vo.DictVO;
import com.wy.result.Result;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "字典类型")
@Api(tags = "字典API")
@RestController
@RequestMapping("dict")
public class DictController extends AbstractController<DictEntity, DictVO, DictQuery, DictService> {

	@GetMapping("list/sql")
	@Operation(summary = "动态SQL数据")
	public Result<List<DictItemVO>> listSql(Long id) {
		List<DictItemVO> list = baseService.getDictSql(id);
		return Result.ok(list);
	}

	@GetMapping("all")
	@Operation(summary = "全部字典数据")
	public Result<List<DictVO>> all() {
		List<DictVO> dictList = baseService.getDictList();
		return Result.ok(dictList);
	}

	@GetMapping("refreshTransCache")
	@Operation(summary = "刷新字典翻译缓存数据")
	@PreAuthorize("hasAuthority('sys:dict:refreshTransCache')")
	public Result<String> refreshTransCache() {
		baseService.refreshTransCache();
		return Result.ok();
	}
}