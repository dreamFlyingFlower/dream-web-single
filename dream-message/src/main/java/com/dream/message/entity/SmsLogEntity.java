package com.dream.message.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:16:07
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sms_log")
public class SmsLogEntity {

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 平台ID
	 */
	private Long platformId;

	/**
	 * 平台类型
	 */
	private Integer platform;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态 0:失败 1:成功
	 */
	private Integer status;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 异常信息
	 */
	private String error;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
}