package com.dream.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.system.convert.OrgConvert;
import com.dream.system.entity.OrgEntity;
import com.dream.system.query.OrgQuery;
import com.dream.system.service.OrgService;
import com.dream.system.vo.OrgVO;
import com.wy.result.Result;

import dream.framework.core.constant.ConstCore;
import dream.framework.mybatis.plus.controller.MyBatisPlusController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 组织机构API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "组织机构API")
@RestController
@RequestMapping("sys/org")
public class OrgController extends MyBatisPlusController<OrgEntity, OrgVO, OrgQuery, OrgService> {

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