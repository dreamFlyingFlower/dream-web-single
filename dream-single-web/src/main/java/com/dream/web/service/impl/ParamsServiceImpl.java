package com.dream.web.service.impl;

import java.rmi.ServerException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.ParamsQuery;
import com.dream.system.web.vo.ParamsVO;
import com.dream.web.convert.ParamsConvert;
import com.dream.web.entity.ParamsEntity;
import com.dream.web.mapper.ParamsMapper;
import com.dream.web.service.ParamsService;
import com.electric.framework.page.PageResult;
import com.electric.framework.utils.JsonUtils;
import com.electric.system.cache.SysParamsCache;
import com.electric.system.convert.SysParamsConvert;
import com.electric.system.query.SysParamsQuery;
import com.electric.system.vo.SysParamsVO;

import cn.hutool.core.util.StrUtil;

/**
 * 参数管理
 *
 * @author 飞花梦影
 * @date 2023-08-07 16:53:38
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
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
	public void save(ParamsVO vo) {
		// 判断 参数键 是否存在
		boolean exist = baseMapper.isExist(vo.getParamKey());
		if (exist) {
			throw new ServerException("参数键已存在");
		}

		ParamsEntity entity = baseConvert.convert(vo);

		baseMapper.insert(entity);

		// 保存到缓存
		paramsCache.save(entity.getParamKey(), entity.getParamValue());
	}

	@Override
	public void update(ParamsVO vo) {
		ParamsEntity entity = baseMapper.selectById(vo.getId());

		// 如果 参数键 修改过
		if (!StrUtil.equalsIgnoreCase(entity.getParamKey(), vo.getParamKey())) {
			// 判断 新参数键 是否存在
			boolean exist = baseMapper.isExist(vo.getParamKey());
			if (exist) {
				throw new ServerException("参数键已存在");
			}

			// 删除修改前的缓存
			sysParamsCache.del(entity.getParamKey());
		}

		// 修改数据
		updateById(SysParamsConvert.INSTANCE.convert(vo));

		// 保存到缓存
		sysParamsCache.save(vo.getParamKey(), vo.getParamValue());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<Long> idList) {
		// 查询列表
		List<ParamsEntity> list = baseMapper.selectBatchIds(idList);

		// 删除数据
		removeByIds(idList);

		// 删除缓存
		Object[] keys = list.stream().map(ParamsEntity::getParamKey).toArray(Object[]::new);
		sysParamsCache.del(keys);
	}

	@Override
	public String getString(String paramKey) {
		String value = sysParamsCache.get(paramKey);
		if (StrUtil.isBlank(value)) {
			throw new ServerException("参数不能为空，paramKey：" + paramKey);
		}

		return value;
	}

	@Override
	public int getInt(String paramKey) {
		String value = getString(paramKey);
		return Integer.parseInt(value);
	}

	@Override
	public boolean getBoolean(String paramKey) {
		String value = getString(paramKey);
		return Boolean.parseBoolean(value);
	}

	@Override
	public <T> T getJSONObject(String paramKey, Class<T> valueType) {
		String value = getString(paramKey);
		return JsonUtils.parseObject(value, valueType);
	}
}