package com.dream.web.service;

import java.util.List;

import com.dream.basic.web.service.BaseService;
import com.dream.web.entity.Dict;
import com.dream.web.query.DictQuery;
import com.dream.web.vo.DictItemVO;
import com.dream.web.vo.DictVO;

/**
 * 
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface DictService extends BaseService<Dict, DictVO, DictQuery> {

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