package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.system.entity.ButtonEntity;
import com.dream.system.query.ButtonQuery;
import com.dream.system.service.ButtonService;
import com.dream.system.vo.ButtonVO;

import dream.framework.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 按钮API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "按钮API")
@RestController
@RequestMapping("button")
public class ButtonController extends AbstractController<ButtonEntity, ButtonVO, ButtonQuery, ButtonService> {

}