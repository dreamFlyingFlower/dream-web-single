package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.DictConvert;
import com.dream.web.entity.Dict;
import com.dream.web.mapper.DictMapper;
import com.dream.web.query.DictQuery;
import com.dream.web.service.DictService;
import com.dream.web.vo.DictDTO;

/**
 * 字典表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("dictService")
public class DictServiceImpl extends AbstractServiceImpl<Dict, DictDTO, DictQuery, DictConvert, DictMapper>
        implements DictService {

}