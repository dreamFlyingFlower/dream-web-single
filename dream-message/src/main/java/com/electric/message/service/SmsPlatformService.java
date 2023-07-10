package com.electric.message.service;

import com.electric.framework.page.PageResult;
import com.electric.framework.service.BaseService;
import com.electric.message.entity.SmsPlatformEntity;
import com.electric.message.query.SmsPlatformQuery;
import com.electric.message.sms.config.SmsConfig;
import com.electric.message.vo.SmsPlatformVO;

import java.util.List;

/**
 * 短信平台
 *
 * @author  
 */
public interface SmsPlatformService extends BaseService<SmsPlatformEntity> {

    PageResult<SmsPlatformVO> page(SmsPlatformQuery query);

    /**
     * 启用的短信平台列表
     */
    List<SmsConfig> listByEnable();

    void save(SmsPlatformVO vo);

    void update(SmsPlatformVO vo);

    void delete(List<Long> idList);

}