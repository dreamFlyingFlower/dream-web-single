package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.AttachmentEntity;
import com.dream.web.query.AttachmentQuery;

@Mapper
public interface AttachmentMapper extends BaseMappers<AttachmentEntity, AttachmentQuery> {

}