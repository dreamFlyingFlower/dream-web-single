package com.dream.framework.handler;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;

import dream.framework.mybatis.plus.handler.MyBatisPlusHandler;
import dream.framework.mybatis.plus.properties.MyBatisPlusProperties;

/**
 * mybatis-plus 自动填充字段
 *
 * @author 飞花梦影
 * @date 2023-08-09 16:54:53
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Component
@EnableConfigurationProperties(MyBatisPlusProperties.class)
public class FieldMetaObjectHandler extends MyBatisPlusHandler {

	private final static String CREATOR = "createUser";

	private final static String UPDATER = "updateUser";

	public FieldMetaObjectHandler(MyBatisPlusProperties myBatisPlusProperties) {
		super(myBatisPlusProperties);
	}

	@Override
	public void insertFill(MetaObject metaObject) {
		super.insertFill(metaObject);
		SecurityUserDetails user = SecurityHelper.getUser();
		strictInsertFill(metaObject, CREATOR, Long.class, null == user ? null : user.getId());
		strictInsertFill(metaObject, UPDATER, Long.class, null == user ? null : user.getId());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		super.updateFill(metaObject);
		strictUpdateFill(metaObject, UPDATER, Long.class, SecurityHelper.getUserId());
	}
}