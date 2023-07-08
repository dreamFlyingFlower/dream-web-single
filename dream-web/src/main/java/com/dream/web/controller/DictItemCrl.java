package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.DictItem;
import com.dream.web.query.DictItemQuery;
import com.dream.web.vo.DictItemDTO;

import io.swagger.annotations.Api;

/**
 * API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "字典详情API")
@RestController
@RequestMapping("dictItem")
public class DictItemCrl extends AbstractController<DictItem, DictItemDTO, DictItemQuery> {

}