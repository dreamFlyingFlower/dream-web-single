package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.web.query.MenuQuery;
import com.dream.system.web.vo.MenuVO;
import com.dream.web.entity.Menu;
import com.dream.web.service.MenuService;

import io.swagger.annotations.Api;

/**
 * 菜单表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "菜单表API")
@RestController
@RequestMapping("menu")
public class MenuController extends AbstractController<Menu, MenuVO, MenuQuery, MenuService> {

}