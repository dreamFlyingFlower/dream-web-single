package com.dream.system.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.convert.AttachmentConvert;
import com.dream.system.entity.AttachmentEntity;
import com.dream.system.mapper.AttachmentMapper;
import com.dream.system.query.AttachmentQuery;
import com.dream.system.service.AttachmentService;
import com.dream.system.vo.AttachmentVO;

import lombok.AllArgsConstructor;

/**
 * 附件管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:31:43
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class AttachmentServiceImpl extends
		AbstractServiceImpl<AttachmentEntity, AttachmentVO, AttachmentQuery, AttachmentConvert, AttachmentMapper>
		implements AttachmentService {

}