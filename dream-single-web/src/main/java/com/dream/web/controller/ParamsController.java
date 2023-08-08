package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.ParamsEntity;
import com.dream.web.query.ParamsQuery;
import com.dream.web.service.ParamsService;
import com.dream.web.vo.ParamsVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 参数管理API
 *
 * @author 飞花梦影
 * @date 2023-08-08 14:37:24
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/params")
@Tag(name = "参数管理API")
public class ParamsController extends AbstractController<ParamsEntity, ParamsVO, ParamsQuery, ParamsService> {

}