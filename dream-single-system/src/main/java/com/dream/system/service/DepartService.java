package com.dream.system.service;

import com.dream.system.entity.DepartEntity;
import com.dream.system.query.DepartQuery;
import com.dream.system.vo.DepartVO;

import dream.framework.mybatis.plus.service.BaseServices;

/**
 * 部门表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public interface DepartService extends BaseServices<DepartEntity, DepartVO, DepartQuery> {

}