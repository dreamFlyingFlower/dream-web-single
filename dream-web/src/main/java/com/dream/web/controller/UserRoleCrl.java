package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.framework.web.query.UserRoleQuery;
import com.dream.framework.web.vo.UserRoleVO;
import com.dream.web.entity.UserRole;

import io.swagger.annotations.Api;

/**
 * 账号-角色关系API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "账号-角色关系API")
@RestController
@RequestMapping("userRole")
public class UserRoleCrl extends AbstractController<UserRole, UserRoleVO, UserRoleQuery> {

}