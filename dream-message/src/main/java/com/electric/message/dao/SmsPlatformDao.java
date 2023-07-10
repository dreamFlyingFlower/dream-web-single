package com.electric.message.dao;

import com.electric.framework.mapper.BaseMappers;
import com.electric.message.entity.SmsPlatformEntity;

import org.apache.ibatis.annotations.Mapper;

/**
* 短信平台
*
* @author  
*/
@Mapper
public interface SmsPlatformDao extends BaseMappers<SmsPlatformEntity> {
	
}