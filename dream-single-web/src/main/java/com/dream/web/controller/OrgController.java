package com.dream.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.core.constant.ConstCore;
import com.dream.basic.web.controller.AbstractController;
import com.dream.web.convert.OrgConvert;
import com.dream.web.entity.OrgEntity;
import com.dream.web.query.OrgQuery;
import com.dream.web.service.OrgService;
import com.dream.web.vo.OrgVO;
import com.wy.result.Result;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

/**
 * 组织机构表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "组织机构表API")
@RestController
@RequestMapping("org")
public class OrgController extends AbstractController<OrgEntity, OrgVO, OrgQuery, OrgService> {

	@GetMapping("{id}")
	@Operation(summary = "信息")
	public Result<OrgVO> get(@PathVariable("id") Long id) {
		OrgEntity entity = baseService.getById(id);
		OrgVO vo = OrgConvert.INSTANCE.convertt(entity);
		// 获取上级机构名称
		if (!ConstCore.ROOT.equals(entity.getPid())) {
			OrgEntity parentEntity = baseService.getById(entity.getPid());
			vo.setParentName(parentEntity.getOrgName());
		}
		return Result.ok(vo);
	}
}