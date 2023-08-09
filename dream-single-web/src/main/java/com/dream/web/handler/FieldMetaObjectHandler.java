package com.dream.web.handler;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dream.web.helper.SecurityHelper;
import com.dream.web.security.SecurityUserDetails;

/**
 * mybatis-plus 自动填充字段
 *
 * @author 飞花梦影
 * @date 2023-08-09 16:54:53
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {

	private final static String CREATE_TIME = "createTime";

	private final static String CREATOR = "creator";

	private final static String UPDATE_TIME = "updateTime";

	private final static String UPDATER = "updater";

	private final static String VERSION = "version";

	private final static String DELETED = "deleted";

	@Override
	public void insertFill(MetaObject metaObject) {
		SecurityUserDetails user = SecurityHelper.getUser();
		Date date = new Date();

		// 创建者
		strictInsertFill(metaObject, CREATOR, Long.class, null == user ? null : user.getId());
		// 创建时间
		strictInsertFill(metaObject, CREATE_TIME, Date.class, date);
		// 更新者
		strictInsertFill(metaObject, UPDATER, Long.class, null == user ? null : user.getId());
		// 更新时间
		strictInsertFill(metaObject, UPDATE_TIME, Date.class, date);
		// 版本号
		strictInsertFill(metaObject, VERSION, Integer.class, 0);
		// 删除标识
		strictInsertFill(metaObject, DELETED, Integer.class, 0);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// 更新者
		strictUpdateFill(metaObject, UPDATER, Long.class, SecurityHelper.getUserId());
		// 更新时间
		strictUpdateFill(metaObject, UPDATE_TIME, Date.class, new Date());
	}
}