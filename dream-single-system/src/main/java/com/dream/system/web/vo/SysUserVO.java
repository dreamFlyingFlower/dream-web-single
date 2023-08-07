package com.dream.system.web.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.electric.system.entity.SysOrgEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户VO
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 15:07:49
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Schema(description = "用户VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	private String userCode;

	@Schema(description = "用户名", required = true)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Schema(description = "密码")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Schema(description = "姓名", required = true)
	@NotBlank(message = "姓名不能为空")
	private String realName;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "性别 0：男   1：女   2：未知", required = true)
	@Range(min = 0, max = 2, message = "性别不正确")
	private Integer gender;

	@Schema(description = "邮箱")
	@Email(message = "邮箱格式不正确")
	private String email;

	@Schema(description = "手机号", required = true)
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	@Schema(description = "机构ID", required = true)
	@NotNull(message = "机构ID不能为空")
	@Trans(type = TransType.SIMPLE, target = SysOrgEntity.class, fields = "name", ref = "orgName")
	private Long orgId;

	@Schema(description = "状态 0：停用    1：正常", required = true)
	@Range(min = 0, max = 1, message = "用户状态不正确")
	private Integer status;

	@Schema(description = "角色ID列表")
	private List<Long> roleIdList;

	private String postName;

	@Schema(description = "岗位证书")
	private String userCert;

	@Schema(description = "岗位ID列表")
	private List<Long> postIdList;

	@Schema(description = "超级管理员   0：否   1：是")
	private Integer superAdmin;

	@Schema(description = "机构名称")
	private String orgName;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;
}