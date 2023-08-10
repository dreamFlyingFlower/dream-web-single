package com.dream.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dream.basic.core.constant.ConstCore;
import com.dream.basic.web.convert.BaseConvert;
import com.dream.basic.web.interceptor.DataScope;
import com.dream.basic.web.mapper.BaseMappers;
import com.dream.basic.web.query.AbstractQuery;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;

/**
 * 通用业务实现类
 * 
 * @auther 飞花梦影
 * @date 2021-05-05 00:14:42
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public abstract class AbstractScopeServiceImpl<T, D, Q extends AbstractQuery, C extends BaseConvert<T, D>,
		M extends BaseMappers<T, D, Q>> extends AbstractServiceImpl<T, D, Q, C, M> {

	/**
	 * MyBatis-Plus 数据权限
	 * 
	 * @param queryWrapper 查询条件
	 */
	protected void dataScopeWrapper(LambdaQueryWrapper<T> queryWrapper) {
		DataScope dataScope = getDataScope(null, null);
		if (dataScope != null) {
			queryWrapper.apply(dataScope.getSqlFilter());
		}
	}

	/**
	 * 原生SQL 数据权限
	 *
	 * @param tableAlias 表别名,多表关联时,需要填写表别名
	 * @param orgIdAlias 机构ID别名,null;表示org_id
	 * @return 返回数据权限
	 */
	protected DataScope getDataScope(String tableAlias, String orgIdAlias) {
		SecurityUserDetails user = SecurityHelper.getUser();
		// 如果是超级管理员，则不进行数据过滤
		if (user.getSuperAdmin().equals(ConstCore.SUPER_ADMIN)) {
			return null;
		}

		// 如果为null，则设置成空字符串
		if (tableAlias == null) {
			tableAlias = "";
		}

		// 获取表的别名
		if (StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}

		StringBuilder sqlFilter = new StringBuilder();
		sqlFilter.append(" (");

		// 数据权限范围
		List<Long> dataScopeList = user.getDataScopeList();
		// 全部数据权限
		if (dataScopeList == null) {
			return null;
		}
		// 数据过滤
		if (dataScopeList.size() > 0) {
			if (StringUtils.isBlank(orgIdAlias)) {
				orgIdAlias = "org_id";
			}
			sqlFilter.append(tableAlias).append(orgIdAlias);

			sqlFilter.append(" in(")
					.append(dataScopeList.stream().map(t -> t.toString()).collect(Collectors.joining(","))).append(")");

			sqlFilter.append(" or ");
		}

		// 查询本人数据
		sqlFilter.append(tableAlias).append("creator").append("=").append(user.getId());

		sqlFilter.append(")");

		return new DataScope(sqlFilter.toString());
	}
}