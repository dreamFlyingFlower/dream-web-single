package com.dream.web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.electric.framework.mapper.BaseMappers;
import com.electric.system.entity.SysUserEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author  
 */
@Mapper
public interface SysUserMapper extends BaseMappers<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(@Param("id") Long id);

	List<SysUserEntity> getRoleUserList(Map<String, Object> params);
	
	Integer updateUserCert(Long id);

	default SysUserEntity getByUsername(String username){
		return this.selectOne(new QueryWrapper<SysUserEntity>().eq("username", username));
	}

	default SysUserEntity getByMobile(String mobile){
		return this.selectOne(new QueryWrapper<SysUserEntity>().eq("mobile", mobile));
	}
}