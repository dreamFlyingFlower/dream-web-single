package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.entity.AttachmentEntity;
import com.dream.system.query.AttachmentQuery;
import com.dream.system.vo.AttachmentVO;

@Mapper
public interface AttachmentMapper extends BaseMappers<AttachmentEntity, AttachmentVO, AttachmentQuery> {

}