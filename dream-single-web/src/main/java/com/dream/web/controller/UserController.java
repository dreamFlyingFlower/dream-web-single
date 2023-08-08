package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.UserEntity;
import com.dream.web.query.UserQuery;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserVO;

import io.swagger.annotations.Api;

/**
 * 用户表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "用户表API")
@RestController
@RequestMapping("user")
public class UserController extends AbstractController<UserEntity, UserVO, UserQuery, UserService> {

}