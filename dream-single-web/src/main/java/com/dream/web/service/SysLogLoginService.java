package com.dream.web.service;

import com.electric.framework.page.PageResult;
import com.electric.framework.service.BaseService;
import com.electric.system.entity.SysLogLoginEntity;
import com.electric.system.query.SysLogLoginQuery;
import com.electric.system.vo.SysLogLoginVO;

/**
 * 登录日志
 *
 * @author  
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    /**
     * Page result.
     *
     * @param query the query
     * @return the page result
     */
    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);

    /**
     * 导出登录日志
     */
    void export();
}