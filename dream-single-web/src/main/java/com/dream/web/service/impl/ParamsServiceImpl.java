package com.dream.web.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.cache.ParamsCache;
import com.dream.web.convert.ParamsConvert;
import com.dream.web.entity.ParamsEntity;
import com.dream.web.mapper.ParamsMapper;
import com.dream.web.query.ParamsQuery;
import com.dream.web.service.ParamsService;
import com.dream.web.vo.ParamsVO;
import com.wy.lang.StrTool;
import com.wy.result.ResultException;

import lombok.AllArgsConstructor;

/**
 * 参数管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:53:38
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class ParamsServiceImpl extends
		AbstractServiceImpl<ParamsEntity, ParamsVO, ParamsQuery, ParamsConvert, ParamsMapper> implements ParamsService {

	private final ParamsCache paramsCache;

	@PostConstruct
	public void init() {
		// 查询列表
		List<ParamsEntity> list = baseMapper.selectList(null);
		// 保存到缓存
		paramsCache.saveList(list);
	}

	@Override
	public ParamsEntity add(ParamsVO vo) {
		ParamsEntity paramsEntity = super.add(vo);
		paramsCache.save(paramsEntity.getParamKey(), paramsEntity.getParamValue());
		return paramsEntity;
	}

	@Override
	public Boolean edit(ParamsVO vo) {
		ParamsEntity entity = baseMapper.selectById(vo.getId());
		if (!StrTool.equalsIgnoreCase(entity.getParamKey(), vo.getParamKey())) {
			boolean exist = baseMapper.isExist(vo.getParamKey());
			if (exist) {
				throw new ResultException("参数键已存在");
			}

			// 删除修改前的缓存
			paramsCache.del(entity.getParamKey());
		}
		// 修改数据
		updateById(baseConvert.convert(vo));
		// 保存到缓存
		paramsCache.save(vo.getParamKey(), vo.getParamValue());
		return true;
	}

	@Override
	public Boolean deleteByIds(List<Serializable> ids) {
		// 查询列表
		List<ParamsEntity> list = baseMapper.selectBatchIds(ids);
		// 删除数据
		removeByIds(ids);
		// 删除缓存
		Object[] keys = list.stream().map(ParamsEntity::getParamKey).toArray(Object[]::new);
		paramsCache.del(keys);
		return true;
	}

	@Override
	public String getString(String paramKey) {
		String value = paramsCache.get(paramKey);
		if (StrTool.isBlank(value)) {
			throw new ResultException("参数不能为空,paramKey：" + paramKey);
		}
		return value;
	}
}