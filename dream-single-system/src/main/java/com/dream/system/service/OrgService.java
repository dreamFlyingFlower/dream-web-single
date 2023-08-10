package com.dream.system.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.system.entity.OrgEntity;
import com.dream.system.query.OrgQuery;
import com.dream.system.vo.OrgVO;

/**
 * 组织机构表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface OrgService extends BaseService<OrgEntity, OrgVO, OrgQuery> {

	/**
	 * 根据机构ID,获取子机构ID列表(包含本机构ID)
	 * 
	 * @param id 机构ID
	 */
	List<Long> getSubOrgIdList(Long id);
}