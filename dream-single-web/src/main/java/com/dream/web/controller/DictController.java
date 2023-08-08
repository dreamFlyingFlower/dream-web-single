package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.DictEntity;
import com.dream.web.query.DictQuery;
import com.dream.web.service.DictService;
import com.dream.web.vo.DictVO;

import io.swagger.annotations.Api;

/**
 * API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "字典API")
@RestController
@RequestMapping("dict")
public class DictController extends AbstractController<DictEntity, DictVO, DictQuery, DictService> {

}