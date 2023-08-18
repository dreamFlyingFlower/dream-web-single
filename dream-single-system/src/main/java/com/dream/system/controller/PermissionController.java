package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.system.entity.PermissionEntity;
import com.dream.system.query.PermissionQuery;
import com.dream.system.service.PermissionService;
import com.dream.system.vo.PermissionVO;

import dream.framework.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 权限API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "权限API")
@RestController
@RequestMapping("permission")
public class PermissionController
		extends AbstractController<PermissionEntity, PermissionVO, PermissionQuery, PermissionService> {

}