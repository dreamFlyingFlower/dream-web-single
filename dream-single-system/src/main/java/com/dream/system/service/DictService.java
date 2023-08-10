package com.dream.system.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.system.entity.DictEntity;
import com.dream.system.query.DictQuery;
import com.dream.system.vo.DictItemVO;
import com.dream.system.vo.DictVO;

/**
 * 
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface DictService extends BaseService<DictEntity, DictVO, DictQuery> {

	/**
	 * 获取动态SQL数据
	 */
	List<DictItemVO> getDictSql(Long id);

	/**
	 * 获取全部字典列表
	 */
	List<DictVO> getDictList();

	/**
	 * 刷新字典缓存
	 */
	void refreshTransCache();
}