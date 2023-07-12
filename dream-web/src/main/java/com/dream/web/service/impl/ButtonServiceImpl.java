package com.dream.web.service.impl;

import org.springframework.stereotype.Service;

import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.framework.web.query.ButtonQuery;
import com.dream.framework.web.vo.ButtonDTO;
import com.dream.web.convert.ButtonConvert;
import com.dream.web.entity.Button;
import com.dream.web.mapper.ButtonMapper;
import com.dream.web.service.ButtonService;

/**
 * 按钮表Service
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Service("buttonService")
public class ButtonServiceImpl extends AbstractServiceImpl<Button, ButtonDTO, ButtonQuery, ButtonConvert, ButtonMapper>
		implements ButtonService {

}