package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.Depart;
import com.dream.web.query.DepartQuery;
import com.dream.web.vo.DepartDTO;

import io.swagger.annotations.Api;

/**
 * 部门表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "部门表API")
@RestController
@RequestMapping("depart")
public class DepartCrl extends AbstractController<Depart, DepartDTO, DepartQuery> {

}