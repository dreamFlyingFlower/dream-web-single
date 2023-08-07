package com.dream.web.mapper;

import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysOrgEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 机构管理
 * 
 * @author  
 */
@Mapper
public interface SysOrgMapper extends BaseMappers<SysOrgEntity> {

    List<SysOrgEntity> getList(Map<String, Object> params);

    /**
     * 获取所有机构的id、pid列表
     */
    List<SysOrgEntity> getIdAndPidList();

}