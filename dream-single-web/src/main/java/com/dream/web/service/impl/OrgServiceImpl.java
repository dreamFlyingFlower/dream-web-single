package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.constant.ConstCommon;
import com.dream.system.utils.TreeUtils;
import com.dream.web.convert.OrgConvert;
import com.dream.web.entity.OrgEntity;
import com.dream.web.entity.UserEntity;
import com.dream.web.mapper.OrgMapper;
import com.dream.web.query.OrgQuery;
import com.dream.web.service.OrgService;
import com.dream.web.service.UserService;
import com.dream.web.vo.OrgVO;
import com.wy.result.ResultException;

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
public class OrgServiceImpl extends AbstractServiceImpl<OrgEntity, OrgVO, OrgQuery, OrgConvert, OrgMapper>
		implements OrgService {

	private final UserService userService;

	@Override
	public List<OrgVO> getList() {
		Map<String, Object> params = new HashMap<>();

		// 数据权限
		params.put(ConstCommon.DATA_SCOPE, getDataScope("t1", "id"));

		// 机构列表
		List<OrgEntity> entityList = baseMapper.getList(params);

		return TreeUtils.build(baseConvert.convertt(entityList));
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
	public Boolean deleteById(Serializable id) {
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