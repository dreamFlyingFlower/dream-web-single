package com.dream.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.OrgEntity;
import com.dream.web.query.OrgQuery;

/**
 * 组织机构表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface OrgMapper extends BaseMappers<OrgEntity, OrgQuery> {

	/**
	 * 获取所有机构的id、pid列表
	 */
	List<OrgEntity> getIdAndPidList();
}