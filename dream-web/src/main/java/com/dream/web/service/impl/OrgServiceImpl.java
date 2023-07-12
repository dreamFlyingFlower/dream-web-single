package com.dream.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.framework.web.query.OrgQuery;
import com.dream.framework.web.vo.OrgDTO;
import com.dream.web.convert.OrgConvert;
import com.dream.web.entity.Org;
import com.dream.web.mapper.OrgMapper;
import com.dream.web.service.OrgService;

/**
 * 组织机构表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("orgService")
public class OrgServiceImpl extends AbstractServiceImpl<Org, OrgDTO, OrgQuery, OrgConvert, OrgMapper>
		implements OrgService {

	@Override
	public List<Long> getSubOrgIdList(Long id) {
		// 所有机构的id、pid列表
		List<Org> orgList = baseMapper.getIdAndPidList();

		// 递归查询所有子机构ID列表
		List<Long> subIdList = new ArrayList<>();
		getTree(id, orgList, subIdList);

		// 本机构也添加进去
		subIdList.add(id);

		return subIdList;
	}

	private void getTree(Long id, List<Org> orgList, List<Long> subIdList) {
		for (Org org : orgList) {
			if (org.getPid().equals(id)) {
				getTree(org.getId(), orgList, subIdList);
				subIdList.add(org.getId());
			}
		}
	}
}