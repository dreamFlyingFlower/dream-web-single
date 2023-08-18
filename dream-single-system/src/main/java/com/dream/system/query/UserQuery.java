package com.dream.system.query;

import dream.framework.web.query.AbstractQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户表查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "用户表查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends AbstractQuery {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户ID")
	private Long id;

	@Schema(description = "用户名,唯一")
	private String username;

	@Schema(description = "姓名")
	private String realName;

	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "性别")
	private Integer gender;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "邮箱")
	private String email;

	@Schema(description = "岗位证书")
	private Boolean userCert;

	@Schema(description = "部门ID")
	private Long departId;

	@Schema(description = "组织机构ID")
	private Long orgId;

	@Schema(description = "用户状态,见字典user_status")
	private Integer status;
}