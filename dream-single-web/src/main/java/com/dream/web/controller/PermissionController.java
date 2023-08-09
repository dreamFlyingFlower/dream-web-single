package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.PermissionEntity;
import com.dream.web.query.PermissionQuery;
import com.dream.web.service.PermissionService;
import com.dream.web.vo.PermissionVO;

import io.swagger.annotations.Api;

/**
 * 权限API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "权限API")
@RestController
@RequestMapping("permission")
public class PermissionController
		extends AbstractController<PermissionEntity, PermissionVO, PermissionQuery, PermissionService> {

}