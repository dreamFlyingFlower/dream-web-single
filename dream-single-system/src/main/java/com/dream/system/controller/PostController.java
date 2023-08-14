package com.dream.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.system.entity.PostEntity;
import com.dream.system.query.PostQuery;
import com.dream.system.service.PostService;
import com.dream.system.vo.PostVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 岗位管理API
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:18:30
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/post")
@Tag(name = "岗位管理API")
public class PostController extends AbstractController<PostEntity, PostVO, PostQuery, PostService> {

}