package com.dream.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.framework.constant.ConstCommon;
import com.dream.message.cache.SmsPlatformCache;
import com.dream.message.convert.SmsPlatformConvert;
import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.mapper.SmsPlatformMapper;
import com.dream.message.query.SmsPlatformQuery;
import com.dream.message.service.SmsPlatformService;
import com.dream.message.sms.config.SmsConfig;
import com.dream.message.vo.SmsPlatformVO;
import com.wy.lang.StrTool;
import com.wy.result.Result;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 00:24:38
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
public class SmsPlatformServiceImpl extends
		AbstractServiceImpl<SmsPlatformEntity, SmsPlatformVO, SmsPlatformQuery, SmsPlatformConvert, SmsPlatformMapper>
		implements SmsPlatformService {

	@Autowired
	private SmsPlatformCache smsPlatformCache;

	@Override
	public Result<List<SmsPlatformVO>> page(SmsPlatformQuery query) {
		IPage<SmsPlatformEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
		return Result.page(baseConvert.convertt(page.getRecords()), page.getCurrent(), page.getSize(), page.getTotal());
	}

	private LambdaQueryWrapper<SmsPlatformEntity> getWrapper(SmsPlatformQuery query) {
		LambdaQueryWrapper<SmsPlatformEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(query.getPlatform() != null, SmsPlatformEntity::getPlatform, query.getPlatform());
		wrapper.like(StrTool.isNotBlank(query.getSignName()), SmsPlatformEntity::getSignName, query.getSignName());
		return wrapper;
	}

	@Override
	public List<SmsConfig> listByEnable() {
		// 从缓存读取
		List<SmsConfig> cacheList = smsPlatformCache.list();
		// 如果缓存没有,则从DB读取,然后保存到缓存里
		if (cacheList == null) {
			List<SmsPlatformEntity> list = this.list(
					new LambdaQueryWrapper<SmsPlatformEntity>().in(SmsPlatformEntity::getStatus, ConstCommon.ENABLE));
			cacheList = SmsPlatformConvert.INSTANCE.convertList2(list);
			smsPlatformCache.save(cacheList);
		}
		return cacheList;
	}

	@Override
	public void save(SmsPlatformVO vo) {
		SmsPlatformEntity entity = SmsPlatformConvert.INSTANCE.convert(vo);
		baseMapper.insert(entity);
		smsPlatformCache.delete();
	}

	@Override
	public void update(SmsPlatformVO vo) {
		SmsPlatformEntity entity = SmsPlatformConvert.INSTANCE.convert(vo);
		updateById(entity);
		smsPlatformCache.delete();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<Long> idList) {
		removeByIds(idList);
		smsPlatformCache.delete();
	}
}