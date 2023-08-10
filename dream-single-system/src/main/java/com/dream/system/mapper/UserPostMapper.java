package com.dream.system.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dream.basic.web.mapper.BaseMappers;
import com.dream.system.entity.UserPostEntity;
import com.dream.system.query.UserPostQuery;
import com.dream.system.vo.UserPostVO;

/**
 * 用户岗位关系
 * 
 * @author 飞花梦影
 * @date 2023-08-07 17:23:47
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Mapper
public interface UserPostMapper extends BaseMappers<UserPostEntity, UserPostVO, UserPostQuery> {

	/**
	 * 岗位ID列表
	 * 
	 * @param userId 用户ID
	 */
	List<Long> getPostIdList(@Param("userId") Serializable userId);
}