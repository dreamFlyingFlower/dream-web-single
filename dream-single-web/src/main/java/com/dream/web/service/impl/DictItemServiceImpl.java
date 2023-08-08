package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.DictItemConvert;
import com.dream.web.entity.DictItemEntity;
import com.dream.web.mapper.DictItemMapper;
import com.dream.web.query.DictItemQuery;
import com.dream.web.service.DictItemService;
import com.dream.web.vo.DictItemVO;

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