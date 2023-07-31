package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.framework.web.query.RoleResourceQuery;
import com.dream.framework.web.vo.RoleResourceVO;
import com.dream.web.entity.RoleResource;

import io.swagger.annotations.Api;

/**
 * 角色-权限关系API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "角色-权限关系API")
@RestController
@RequestMapping("roleResource")
public class RoleResourceCrl extends AbstractController<RoleResource, RoleResourceVO, RoleResourceQuery> {

}