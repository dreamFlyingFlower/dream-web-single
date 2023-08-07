package com.dream.web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysParamsEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 参数管理
 *
 * @author  
 */
@Mapper
public interface SysParamsMapper extends BaseMappers<SysParamsEntity> {

    default boolean isExist(String paramKey) {
        return this.exists(new QueryWrapper<SysParamsEntity>().eq("param_key" , paramKey));
    }
}