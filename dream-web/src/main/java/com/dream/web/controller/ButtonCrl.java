package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.framework.web.query.ButtonQuery;
import com.dream.framework.web.vo.ButtonDTO;
import com.dream.web.entity.Button;

import io.swagger.annotations.Api;

/**
 * 按钮表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "按钮表API")
@RestController
@RequestMapping("button")
public class ButtonCrl extends AbstractController<Button, ButtonDTO, ButtonQuery> {

}