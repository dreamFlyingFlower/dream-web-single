package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.UserRoleEntity;
import com.dream.web.query.UserRoleQuery;
import com.dream.web.service.UserRoleService;
import com.dream.web.vo.UserRoleVO;

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
public class UserRoleController extends AbstractController<UserRoleEntity, UserRoleVO, UserRoleQuery, UserRoleService> {

}