package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.web.query.AttachmentQuery;
import com.dream.web.entity.AttachmentEntity;

@Mapper
public interface AttachmentMapper extends BaseMappers<AttachmentEntity, AttachmentQuery> {

}