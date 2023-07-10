package com.electric.message.dao;

import com.electric.framework.mapper.BaseMappers;
import com.electric.message.entity.SmsLogEntity;

import org.apache.ibatis.annotations.Mapper;

/**
* 短信日志
*
* @author  
*/
@Mapper
public interface SmsLogDao extends BaseMappers<SmsLogEntity> {
	
}