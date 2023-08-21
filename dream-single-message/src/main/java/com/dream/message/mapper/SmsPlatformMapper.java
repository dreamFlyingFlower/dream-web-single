package com.dream.message.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.query.SmsPlatformQuery;
import com.dream.message.vo.SmsPlatformVO;

import dream.framework.mybatis.plus.mapper.BaseMappers;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:02:33
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface SmsPlatformMapper extends BaseMappers<SmsPlatformEntity, SmsPlatformVO, SmsPlatformQuery> {

}