package com.dream.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.electric.framework.exception.ServerException;
import com.electric.framework.page.PageResult;
import com.electric.framework.service.impl.BaseServiceImpl;
import com.electric.framework.utils.JsonUtils;
import com.electric.system.cache.SysParamsCache;
import com.electric.system.convert.SysParamsConvert;
import com.electric.system.entity.SysParamsEntity;
import com.electric.system.mapper.SysParamsMapper;
import com.electric.system.query.SysParamsQuery;
import com.electric.system.service.SysParamsService;
import com.electric.system.vo.SysParamsVO;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 参数管理
 *
 * @author
 */
@Service
@AllArgsConstructor
public class SysParamsServiceImpl extends BaseServiceImpl<SysParamsMapper, SysParamsEntity>
		implements SysParamsService {

	private final SysParamsCache sysParamsCache;

	@PostConstruct
	public void init() {
		// 查询列表
		List<SysParamsEntity> list = baseMapper.selectList(null);

		// 保存到缓存
		sysParamsCache.saveList(list);
	}

	@Override
	public PageResult<SysParamsVO> page(SysParamsQuery query) {
		IPage<SysParamsEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

		return new PageResult<>(SysParamsConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
	}

	private LambdaQueryWrapper<SysParamsEntity> getWrapper(SysParamsQuery query) {
		LambdaQueryWrapper<SysParamsEntity> wrapper = Wrappers.lambdaQuery();

		wrapper.like(StrUtil.isNotBlank(query.getParamKey()), SysParamsEntity::getParamKey, query.getParamKey());
		wrapper.eq(StrUtil.isNotBlank(query.getParamValue()), SysParamsEntity::getParamValue, query.getParamValue());
		wrapper.eq(query.getParamType() != null, SysParamsEntity::getParamType, query.getParamType());
		wrapper.orderByDesc(SysParamsEntity::getId);

		return wrapper;
	}

	@Override
	public void save(SysParamsVO vo) {
		// 判断 参数键 是否存在
		boolean exist = baseMapper.isExist(vo.getParamKey());
		if (exist) {
			throw new ServerException("参数键已存在");
		}

		SysParamsEntity entity = SysParamsConvert.INSTANCE.convert(vo);

		baseMapper.insert(entity);

		// 保存到缓存
		sysParamsCache.save(entity.getParamKey(), entity.getParamValue());
	}

	@Override
	public void update(SysParamsVO vo) {
		SysParamsEntity entity = baseMapper.selectById(vo.getId());

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
		List<SysParamsEntity> list = baseMapper.selectBatchIds(idList);

		// 删除数据
		removeByIds(idList);

		// 删除缓存
		Object[] keys = list.stream().map(SysParamsEntity::getParamKey).toArray(Object[]::new);
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