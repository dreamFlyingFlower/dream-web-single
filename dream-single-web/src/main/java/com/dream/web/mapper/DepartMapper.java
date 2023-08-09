package com.dream.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.DepartEntity;
import com.dream.web.query.DepartQuery;
import com.dream.web.vo.DepartVO;

/**
 * 部门表
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface DepartMapper extends BaseMappers<DepartEntity, DepartVO, DepartQuery> {

}