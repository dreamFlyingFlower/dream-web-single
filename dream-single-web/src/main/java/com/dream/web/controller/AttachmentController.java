package com.dream.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.AttachmentEntity;
import com.dream.web.query.AttachmentQuery;
import com.dream.web.service.AttachmentService;
import com.dream.web.vo.AttachmentVO;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 附件管理 API
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:37:49
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("attachment")
@Tag(name = "附件管理")
public class AttachmentController
		extends AbstractController<AttachmentEntity, AttachmentVO, AttachmentQuery, AttachmentService> {

}