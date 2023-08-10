package com.dream.framework.properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 数据库通用配置
 *
 * @author 飞花梦影
 * @date 2022-12-21 17:23:33
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Data
@ConfigurationProperties(prefix = "dream.basic.database")
public class BasicDatabaseProperties {

	private String url;

	private String username;

	private String password;

	private DataSource type;
}