package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.framework.web.query.RoleQuery;
import com.dream.framework.web.vo.RoleVO;
import com.dream.web.entity.Role;

import io.swagger.annotations.Api;

/**
 * 角色信息API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "角色信息API")
@RestController
@RequestMapping("role")
public class RoleCrl extends AbstractController<Role, RoleVO, RoleQuery> {

}