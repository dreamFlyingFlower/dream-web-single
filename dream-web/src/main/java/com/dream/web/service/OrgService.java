package com.dream.web.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.framework.web.query.OrgQuery;
import com.dream.framework.web.vo.OrgVO;
import com.dream.web.entity.Org;

/**
 * 组织机构表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface OrgService extends BaseService<Org, OrgVO, OrgQuery> {

	/**
	 * 根据机构ID,获取子机构ID列表(包含本机构ID)
	 * 
	 * @param id 机构ID
	 */
	List<Long> getSubOrgIdList(Long id);
}