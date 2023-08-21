package com.dream.system.service.impl;

import org.springframework.stereotype.Service;

import com.dream.system.convert.DictItemConvert;
import com.dream.system.entity.DictItemEntity;
import com.dream.system.mapper.DictItemMapper;
import com.dream.system.query.DictItemQuery;
import com.dream.system.service.DictItemService;
import com.dream.system.vo.DictItemVO;

import dream.framework.mybatis.plus.service.impl.AbstractServiceImpl;

/**
 * 字典项Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("dictItemService")
public class DictItemServiceImpl
        extends AbstractServiceImpl<DictItemEntity, DictItemVO, DictItemQuery, DictItemConvert, DictItemMapper>
        implements DictItemService {

}