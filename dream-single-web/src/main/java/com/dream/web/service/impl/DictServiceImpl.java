package com.dream.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.enums.DictSourceEnum;
import com.dream.web.convert.DictConvert;
import com.dream.web.entity.DictEntity;
import com.dream.web.entity.DictItemEntity;
import com.dream.web.mapper.DictItemMapper;
import com.dream.web.mapper.DictMapper;
import com.dream.web.query.DictQuery;
import com.dream.web.service.DictService;
import com.dream.web.vo.DictItemVO;
import com.dream.web.vo.DictVO;
import com.fhs.trans.service.impl.DictionaryTransService;
import com.wy.result.ResultException;

import lombok.AllArgsConstructor;

/**
 * 字典表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("dictService")
@AllArgsConstructor
public class DictServiceImpl extends AbstractServiceImpl<DictEntity, DictVO, DictQuery, DictConvert, DictMapper>
		implements DictService, InitializingBean {

	private final DictItemMapper sysDictDataDao;

	private final DictionaryTransService dictionaryTransService;

	@Override
	public List<DictItemVO> getDictSql(Long id) {
		DictEntity entity = this.getById(id);
		try {
			return sysDictDataDao.getListForSql(entity.getDictSql());
		} catch (Exception e) {
			throw new ResultException("动态SQL执行失败，请检查SQL是否正确！");
		}
	}

	@Override
	public List<DictVO> getDictList() {
		// 全部字典类型列表
		List<DictEntity> typeList = this.list(Wrappers.emptyWrapper());

		// 全部字典数据列表
		QueryWrapper<DictItemEntity> query = new QueryWrapper<DictItemEntity>().orderByAsc("sort");
		List<DictItemEntity> dataList = sysDictDataDao.selectList(query);

		// 全部字典列表
		List<DictVO> dictList = new ArrayList<>(typeList.size());
		for (DictEntity type : typeList) {
			DictVO dict = new DictVO();
			dict.setDictCode(type.getDictCode());

			List<DictItemVO> dictItemVOs = new ArrayList<>();

			for (DictItemEntity data : dataList) {
				if (type.getId().equals(data.getDictId())) {
					dictItemVOs.add(DictItemVO.builder().dictName(data.getDictName()).dictValue(data.getDictValue())
							.labelClass(data.getLabelClass()).build());
				}
			}
			dict.setDictItemVOs(dictItemVOs);

			// 数据来源动态SQL
			if (type.getDictSource() == DictSourceEnum.SQL.getValue()) {
				// 增加动态列表
				String sql = type.getDictSql();
				try {
					dict.setDictItemVOs(sysDictDataDao.getListForSql(sql));
				} catch (Exception e) {
					log.error("增加动态字典异常: type=" + type, e);
				}
			}

			dictList.add(dict);
		}

		return dictList;
	}

	@Override
	public void afterPropertiesSet() {
		refreshTransCache();
	}

	@Override
	public void refreshTransCache() {
		// 异步不阻塞主线程,不会 增加启动用时
		CompletableFuture.supplyAsync(() -> {
			// 获取所有的字典项数据
			List<DictItemEntity> dataList = sysDictDataDao.selectList(new LambdaQueryWrapper<>());
			// 根据类型分组
			Map<Long, List<DictItemEntity>> dictTypeDataMap =
					dataList.stream().collect(Collectors.groupingBy(DictItemEntity::getDictId));
			List<DictEntity> dictTypeEntities = super.list();
			for (DictEntity dictTypeEntity : dictTypeEntities) {
				if (dictTypeDataMap.containsKey(dictTypeEntity.getId())) {
					dictionaryTransService.refreshCache(dictTypeEntity.getDictCode(),
							dictTypeDataMap.get(dictTypeEntity.getId()).stream().collect(
									Collectors.toMap(DictItemEntity::getDictValue, DictItemEntity::getDictName)));
				}
			}
			return null;
		});
	}
}