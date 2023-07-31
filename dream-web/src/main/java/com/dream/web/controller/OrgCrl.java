package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.framework.web.query.OrgQuery;
import com.dream.framework.web.vo.OrgVO;
import com.dream.web.entity.Org;

import io.swagger.annotations.Api;

/**
 * 组织机构表API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "组织机构表API")
@RestController
@RequestMapping("org")
public class OrgCrl extends AbstractController<Org, OrgVO, OrgQuery> {

}