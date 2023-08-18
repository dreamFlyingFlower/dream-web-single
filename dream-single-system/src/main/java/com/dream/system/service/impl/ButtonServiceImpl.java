package com.dream.system.service.impl;

import org.springframework.stereotype.Service;

import com.dream.system.convert.ButtonConvert;
import com.dream.system.entity.ButtonEntity;
import com.dream.system.mapper.ButtonMapper;
import com.dream.system.query.ButtonQuery;
import com.dream.system.service.ButtonService;
import com.dream.system.vo.ButtonVO;

import dream.framework.web.service.impl.AbstractServiceImpl;

/**
 * 按钮表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("buttonService")
public class ButtonServiceImpl extends AbstractServiceImpl<ButtonEntity, ButtonVO, ButtonQuery, ButtonConvert, ButtonMapper>
		implements ButtonService {

}