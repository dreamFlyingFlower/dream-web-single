package com.dream.web.service;

import com.electric.framework.page.PageResult;
import com.electric.framework.service.BaseService;
import com.electric.system.entity.SysDictTypeEntity;
import com.electric.system.query.SysDictTypeQuery;
import com.electric.system.vo.SysDictTypeVO;
import com.electric.system.vo.SysDictVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author  
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageResult<SysDictTypeVO> page(SysDictTypeQuery query);

    void save(SysDictTypeVO vo);

    void update(SysDictTypeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取动态SQL数据
     */
    List<SysDictVO.DictData> getDictSql(Long id);

    /**
     * 获取全部字典列表
     */
    List<SysDictVO> getDictList();

    /**
     * 刷新字典缓存
     */
    void refreshTransCache();

}