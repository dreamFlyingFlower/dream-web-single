package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.LogLoginEntity;
import com.dream.web.query.LogLoginQuery;
import com.dream.web.vo.LogLoginVO;

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