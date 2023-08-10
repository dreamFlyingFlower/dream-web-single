package com.dream.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前登录用户 DTO
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 15:09:34
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Schema(description = "当前登录用户 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserVO {

	/**
	 * tenantId : 1 departmentId : 1 payload : {"res":"res1111111"} username : admin
	 * mobile : 18611106983 userAuthorities : {"ROLE1":["p1","p2"]} clientId :
	 * wanxin-p2p-web-admin
	 */

	private String tenantId;

	private String departId;

	private String payload;

	private String username;

	private String mobile;

	private String userAuthorities;

	private String clientId;
}