package com.dream.framework.handler;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;

/**
 * mybatis-plus 自动填充字段
 *
 * @author 飞花梦影
 * @date 2023-08-09 16:54:53
 * @git {@link https://github.com/dreamFlyingFlower}
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {

	private final static String CREATE_TIME = "createTime";

	private final static String CREATOR = "createUser";

	private final static String UPDATE_TIME = "updateTime";

	private final static String UPDATER = "updateUser";

	private final static String VERSION = "version";

	private final static String DELETED = "deleted";

	@Override
	public void insertFill(MetaObject metaObject) {
		SecurityUserDetails user = SecurityHelper.getUser();
		Date date = new Date();
		strictInsertFill(metaObject, CREATOR, Long.class, null == user ? null : user.getId());
		strictInsertFill(metaObject, CREATE_TIME, Date.class, date);
		strictInsertFill(metaObject, UPDATER, Long.class, null == user ? null : user.getId());
		strictInsertFill(metaObject, UPDATE_TIME, Date.class, date);
		strictInsertFill(metaObject, VERSION, Integer.class, 0);
		strictInsertFill(metaObject, DELETED, Integer.class, 0);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		strictUpdateFill(metaObject, UPDATER, Long.class, SecurityHelper.getUserId());
		strictUpdateFill(metaObject, UPDATE_TIME, Date.class, new Date());
	}
}