package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.system.web.query.DepartQuery;
import com.dream.system.web.vo.DepartVO;
import com.dream.web.convert.DepartConvert;
import com.dream.web.entity.Depart;
import com.dream.web.mapper.DepartMapper;
import com.dream.web.service.DepartService;

/**
 * 部门表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("departService")
public class DepartServiceImpl extends AbstractServiceImpl<Depart, DepartVO, DepartQuery, DepartConvert, DepartMapper> implements DepartService {

}