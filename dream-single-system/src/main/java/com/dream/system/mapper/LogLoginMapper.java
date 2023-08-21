package com.dream.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.system.entity.LogLoginEntity;
import com.dream.system.query.LogLoginQuery;
import com.dream.system.vo.LogLoginVO;

import dream.framework.mybatis.plus.mapper.BaseMappers;

/**
 * 登录日志
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:22:30
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface LogLoginMapper extends BaseMappers<LogLoginEntity, LogLoginVO, LogLoginQuery> {

}