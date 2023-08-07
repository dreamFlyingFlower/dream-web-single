package com.dream.web.mapper;

import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysRoleEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色管理
 * 
 * @author  
 */
@Mapper
public interface SysRoleMapper extends BaseMappers<SysRoleEntity> {

    /**
     * 根据用户ID，获取用户最大的数据范围
     */
    Integer getDataScopeByUserId(@Param("userId") Long userId);

}