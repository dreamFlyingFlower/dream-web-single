package com.electric.message.service;

import com.electric.framework.page.PageResult;
import com.electric.framework.service.BaseService;
import com.electric.message.entity.SmsLogEntity;
import com.electric.message.query.SmsLogQuery;
import com.electric.message.vo.SmsLogVO;

/**
 * 短信日志
 *
 * @author  
 */
public interface SmsLogService extends BaseService<SmsLogEntity> {

    PageResult<SmsLogVO> page(SmsLogQuery query);

}