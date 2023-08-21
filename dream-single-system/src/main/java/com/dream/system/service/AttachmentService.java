package com.dream.system.service;

import com.dream.system.entity.AttachmentEntity;
import com.dream.system.query.AttachmentQuery;
import com.dream.system.vo.AttachmentVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 附件管理表
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:27:34
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
public interface AttachmentService extends BaseServices<AttachmentEntity, AttachmentVO, AttachmentQuery> {

}