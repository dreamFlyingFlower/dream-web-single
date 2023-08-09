package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.AttachmentEntity;
import com.dream.web.query.AttachmentQuery;
import com.dream.web.vo.AttachmentVO;

@Mapper
public interface AttachmentMapper extends BaseMappers<AttachmentEntity, AttachmentVO, AttachmentQuery> {

}