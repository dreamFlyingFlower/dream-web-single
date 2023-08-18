package com.dream.system.service.impl;

import org.springframework.stereotype.Service;

import com.dream.system.convert.DepartConvert;
import com.dream.system.entity.DepartEntity;
import com.dream.system.mapper.DepartMapper;
import com.dream.system.query.DepartQuery;
import com.dream.system.service.DepartService;
import com.dream.system.vo.DepartVO;

import dream.framework.web.service.impl.AbstractServiceImpl;

/**
 * 部门表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("departService")
public class DepartServiceImpl extends AbstractServiceImpl<DepartEntity, DepartVO, DepartQuery, DepartConvert, DepartMapper> implements DepartService {

}