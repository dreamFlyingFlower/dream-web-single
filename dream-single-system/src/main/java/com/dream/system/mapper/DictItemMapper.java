package com.dream.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dream.system.entity.DictItemEntity;
import com.dream.system.query.DictItemQuery;
import com.dream.system.vo.DictItemVO;

import dream.framework.web.mapper.BaseMappers;

/**
 * 字典项
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:24:33
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper
public interface DictItemMapper extends BaseMappers<DictItemEntity, DictItemVO, DictItemQuery> {

	@Select("${sql}")
	List<DictItemVO> getListForSql(@Param("sql") String sql);
}