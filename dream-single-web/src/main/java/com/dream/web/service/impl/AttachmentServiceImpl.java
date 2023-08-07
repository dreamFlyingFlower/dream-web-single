package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.AttachmentQuery;
import com.dream.system.web.vo.AttachmentVO;
import com.dream.web.convert.AttachmentConvert;
import com.dream.web.entity.AttachmentEntity;
import com.dream.web.mapper.AttachmentMapper;
import com.dream.web.service.AttachmentService;

/**
 * 附件管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:31:43
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class AttachmentServiceImpl extends
		AbstractServiceImpl<AttachmentEntity, AttachmentVO, AttachmentQuery, AttachmentConvert, AttachmentMapper>
		implements AttachmentService {

}