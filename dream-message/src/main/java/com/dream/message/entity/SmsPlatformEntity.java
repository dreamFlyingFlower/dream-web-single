package com.dream.message.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 短信平台
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:15:29
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sms_platform")
public class SmsPlatformEntity extends AbstractEntity {

	private static final long serialVersionUID = 5880210234784532332L;

	/**
	 * 平台类型 0:阿里云 1:腾讯云 2:七牛云 3:华为云
	 */
	private Integer platform;

	/**
	 * 短信签名
	 */
	private String signName;

	/**
	 * 短信模板
	 */
	private String templateId;

	/**
	 * 短信应用的ID,如:腾讯云等
	 */
	private String appId;

	/**
	 * 腾讯云国际短信、华为云等需要
	 */
	private String senderId;

	/**
	 * 接入地址,如:华为云
	 */
	private String url;

	/**
	 * AccessKey
	 */
	private String accessKey;

	/**
	 * SecretKey
	 */
	private String secretKey;

	/**
	 * 状态 0:禁用 1:启用
	 */
	private Integer status;
}