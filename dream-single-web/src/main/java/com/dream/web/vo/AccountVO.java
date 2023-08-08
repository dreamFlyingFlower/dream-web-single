package com.dream.web.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户信息 DTO
 * 
 * @author 飞花梦影
 * @date 2022-08-31 14:54:31
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@ApiModel(value = "AccountDTO", description = "账户信息 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountVO implements Serializable {

	private static final long serialVersionUID = 8757533935681350195L;

	/**
	 * 账户ID
	 */
	@ApiModelProperty("账户ID")
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 账户状态
	 */
	@ApiModelProperty("账户状态")
	private Integer status;

	/**
	 * 域:c-c端用户;b-b端用户
	 */
	@ApiModelProperty("域:c-c端用户;b-b端用户")
	private String domain;
}