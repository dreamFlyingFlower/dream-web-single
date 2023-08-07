package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.web.query.PermissionQuery;
import com.dream.system.web.vo.PermissionVO;
import com.dream.web.entity.Permission;
import com.dream.web.service.PermissionService;

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
		extends AbstractController<Permission, PermissionVO, PermissionQuery, PermissionService> {

}