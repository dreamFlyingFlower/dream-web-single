package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.entity.RoleResourceEntity;
import com.dream.system.query.RoleResourceQuery;
import com.dream.system.service.RoleResourceService;
import com.dream.system.vo.RoleResourceVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 角色-权限关系API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "角色-权限关系API")
@RestController
@RequestMapping("roleResource")
public class RoleResourceController
		extends AbstractController<RoleResourceEntity, RoleResourceVO, RoleResourceQuery, RoleResourceService> {

}