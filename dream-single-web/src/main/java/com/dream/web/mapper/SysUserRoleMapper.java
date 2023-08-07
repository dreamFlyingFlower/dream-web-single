package com.dream.web.mapper;

import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysUserRoleEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author  
 */
@Mapper
public interface SysUserRoleMapper extends BaseMappers<SysUserRoleEntity> {

    /**
     * 角色ID列表
     * @param userId  用户ID
     *
     * @return  返回角色ID列表
     */
    List<Long> getRoleIdList(@Param("userId") Long userId);
}