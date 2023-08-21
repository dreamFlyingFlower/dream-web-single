package com.dream.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dream.message.convert.SmsLogConvert;
import com.dream.message.entity.SmsLogEntity;
import com.dream.message.mapper.SmsLogMapper;
import com.dream.message.query.SmsLogQuery;
import com.dream.message.service.SmsLogService;
import com.dream.message.vo.SmsLogVO;
import com.wy.result.Result;

import dream.framework.mybatis.plus.service.impl.AbstractServiceImpl;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:16:25
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class SmsLogServiceImpl extends
		AbstractServiceImpl<SmsLogEntity, SmsLogVO, SmsLogQuery, SmsLogConvert, SmsLogMapper> implements SmsLogService {

	@Override
	public Result<List<SmsLogVO>> page(SmsLogQuery query) {
		IPage<SmsLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
		return Result.page(baseConvert.convertt(page.getRecords()), page.getCurrent(), page.getSize(), page.getTotal());
	}

	private LambdaQueryWrapper<SmsLogEntity> getWrapper(SmsLogQuery query) {
		LambdaQueryWrapper<SmsLogEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(query.getPlatform() != null, SmsLogEntity::getPlatform, query.getPlatform());
		wrapper.like(query.getPlatformId() != null, SmsLogEntity::getPlatformId, query.getPlatformId());
		wrapper.orderByDesc(SmsLogEntity::getId);
		return wrapper;
	}
}