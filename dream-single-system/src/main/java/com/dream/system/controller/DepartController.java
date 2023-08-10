package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.entity.DepartEntity;
import com.dream.system.query.DepartQuery;
import com.dream.system.service.DepartService;
import com.dream.system.vo.DepartVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 部门表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "部门表API")
@RestController
@RequestMapping("depart")
public class DepartController extends AbstractController<DepartEntity, DepartVO, DepartQuery, DepartService> {

}