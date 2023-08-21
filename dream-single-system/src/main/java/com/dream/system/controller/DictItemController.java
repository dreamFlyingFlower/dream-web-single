package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.system.entity.DictItemEntity;
import com.dream.system.query.DictItemQuery;
import com.dream.system.service.DictItemService;
import com.dream.system.vo.DictItemVO;

import dream.framework.mybatis.plus.controller.MyBatisPlusController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 字典详情API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "字典详情API")
@RestController
@RequestMapping("sys/dictItem")
public class DictItemController
		extends MyBatisPlusController<DictItemEntity, DictItemVO, DictItemQuery, DictItemService> {

}