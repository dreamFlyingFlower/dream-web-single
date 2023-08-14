package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.entity.UserRoleEntity;
import com.dream.system.query.UserRoleQuery;
import com.dream.system.service.UserRoleService;
import com.dream.system.vo.UserRoleVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 用户角色API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "用户角色API")
@RestController
@RequestMapping("sys/userRole")
public class UserRoleController extends AbstractController<UserRoleEntity, UserRoleVO, UserRoleQuery, UserRoleService> {

}