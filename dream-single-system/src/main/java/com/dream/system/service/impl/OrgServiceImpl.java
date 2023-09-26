package com.dream.system.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.framework.helper.TreeHelper;
import com.dream.system.convert.OrgConvert;
import com.dream.system.entity.OrgEntity;
import com.dream.system.entity.UserEntity;
import com.dream.system.mapper.OrgMapper;
import com.dream.system.query.OrgQuery;
import com.dream.system.service.OrgService;
import com.dream.system.service.UserService;
import com.dream.system.vo.OrgVO;
import com.wy.result.ResultException;

import dream.framework.core.constant.ConstCore;
import lombok.AllArgsConstructor;

/**
 * 组织机构表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("orgService")
@AllArgsConstructor
public class OrgServiceImpl extends AbstractScopeServiceImpl<OrgEntity, OrgVO, OrgQuery, OrgConvert, OrgMapper>
		implements OrgService {

	private final UserService userService;

	@Override
	public List<OrgVO> list(OrgQuery query) {
		Map<String, Object> params = new HashMap<>();

		// 数据权限
		params.put(ConstCore.DATA_SCOPE, getDataScope("t1", "id"));

		// 机构列表
		List<OrgEntity> entityList = baseMapper.getList(params);

		return TreeHelper.build(baseConvert.convertt(entityList));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(OrgVO vo) {
		OrgEntity entity = baseConvert.convert(vo);

		// 上级机构不能为自身
		if (entity.getId().equals(entity.getPid())) {
			throw new ResultException("上级机构不能为自身");
		}

		// 上级机构不能为下级
		List<Long> subOrgList = getSubOrgIdList(entity.getId());
		if (subOrgList.contains(entity.getPid())) {
			throw new ResultException("上级机构不能为下级");
		}

		updateById(entity);
		return true;
	}

	@Override
	public Boolean delete(Serializable id) {
		// 判断是否有子机构
		long orgCount = count(new QueryWrapper<OrgEntity>().eq("pid", id));
		if (orgCount > 0) {
			throw new ResultException("请先删除子机构");
		}

		// 判断机构下面是否有用户
		long userCount = userService.count(new QueryWrapper<UserEntity>().eq("org_id", id));
		if (userCount > 0) {
			throw new ResultException("机构下面有用户，不能删除");
		}

		// 删除
		removeById(id);
		return true;
	}

	@Override
	public List<Long> getSubOrgIdList(Long id) {
		// 所有机构的id、pid列表
		List<OrgEntity> orgList = baseMapper.getIdAndPidList();

		// 递归查询所有子机构ID列表
		List<Long> subIdList = new ArrayList<>();
		getTree(id, orgList, subIdList);

		// 本机构也添加进去
		subIdList.add(id);

		return subIdList;
	}

	private void getTree(Long id, List<OrgEntity> orgList, List<Long> subIdList) {
		for (OrgEntity org : orgList) {
			if (org.getPid().equals(id)) {
				getTree(org.getId(), orgList, subIdList);
				subIdList.add(org.getId());
			}
		}
	}
}