package com.dream.web.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.dream.basic.web.convert.BaseConvert;
import com.dream.web.entity.UserEntity;
import com.dream.web.security.SecurityUserDetails;
import com.dream.web.vo.UserExcelVO;
import com.dream.web.vo.UserVO;

/**
 * 用户表数据库实体与DTO互转
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:40:21
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert extends BaseConvert<UserEntity, UserVO> {

	UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

	SecurityUserDetails convert2Detail(UserEntity entity);

	List<UserExcelVO> convert2Excel(List<UserEntity> list);

	UserVO convertDetail(SecurityUserDetails userDetail);

	List<UserEntity> convertExcel(List<UserExcelVO> list);
}