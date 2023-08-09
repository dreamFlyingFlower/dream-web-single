package com.dream.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.web.entity.DictItemEntity;
import com.dream.web.query.DictItemQuery;
import com.dream.web.vo.DictItemVO;

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