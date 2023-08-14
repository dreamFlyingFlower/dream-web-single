package com.dream.system.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dream.basic.web.valid.ValidEdit;
import com.dream.system.entity.OrgEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户VO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "用户VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

	private static final long serialVersionUID = 2195467941855219248L;

	/**
	 * 用户ID
	 */
	@Schema(description = "id")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 用户名,唯一
	 */
	@Schema(description = "用户名", requiredMode = RequiredMode.REQUIRED)
	@NotBlank(message = "用户名不能为空")
	private String username;

	/**
	 * 密码
	 */
	@Schema(description = "密码")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	/**
	 * 真实姓名
	 */
	@Schema(description = "姓名", requiredMode = RequiredMode.REQUIRED)
	@NotBlank(message = "姓名不能为空")
	private String realName;

	/**
	 * 昵称
	 */
	@Schema(description = "昵称")
	private String nickname;

	/**
	 * 性别
	 */
	@Schema(description = "性别:1-男;2-女;3:未知", requiredMode = RequiredMode.REQUIRED)
	@Range(min = 1, max = 3, message = "性别不正确")
	private Integer gender;

	/**
	 * 手机号
	 */
	@Schema(description = "手机号", requiredMode = RequiredMode.REQUIRED)
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱")
	@Email(message = "邮箱格式不正确")
	private String email;

	/**
	 * 头像地址
	 */
	@Schema(description = "头像地址")
	private String avatar;

	/**
	 * 岗位证书
	 */
	@Schema(description = "岗位证书")
	private String userCert;

	@Schema(description = "是否超级管理员", hidden = true)
	private Integer superAdmin;

	/**
	 * 部门ID
	 */
	@Schema(description = "部门ID")
	private Long departId;

	/**
	 * 组织机构ID
	 */
	@Schema(description = "机构ID", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "机构ID不能为空")
	@Trans(type = TransType.SIMPLE, target = OrgEntity.class, fields = "name", ref = "orgName")
	private Long orgId;

	@Schema(description = "用户状态,见字典user_status")
	private Integer status;

	@Schema(description = "机构名称")
	private String orgName;

	@Schema(description = "角色ID列表")
	private List<Long> roleIds;

	@Schema(description = "岗位名称")
	private String postName;

	@Schema(description = "岗位ID列表")
	private List<Long> postIds;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private Date createTime;

	/**
	 * 创建人ID
	 */
	@Schema(description = "创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	private Date updateTime;

	/**
	 * 更新人ID
	 */
	@Schema(description = "更新人ID")
	private Long updateUser;

	/**
	 * 帐户是否过期
	 */
	@Builder.Default
	private boolean isAccountNonExpired = true;

	/**
	 * 帐户是否被锁定
	 */
	@Builder.Default
	private boolean isAccountNonLocked = true;

	/**
	 * 密码是否过期
	 */
	@Builder.Default
	private boolean isCredentialsNonExpired = true;

	/**
	 * 帐户是否可用
	 */
	@Builder.Default
	private boolean isEnabled = true;

	/**
	 * 拥有权限集合
	 */
	private Set<String> authoritySet;

	/**
	 * 数据权限范. null表示全部数据权限
	 */
	private List<Long> dataScopeList;

	// @Override
	// @JsonIgnore
	// public Collection<? extends GrantedAuthority> getAuthorities() {
	// return
	// authoritySet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	// }
	//
	// @Override
	// public boolean isAccountNonExpired() {
	// return this.isAccountNonExpired;
	// }
	//
	// @Override
	// public boolean isAccountNonLocked() {
	// return this.isAccountNonLocked;
	// }
	//
	// @Override
	// public boolean isCredentialsNonExpired() {
	// return this.isCredentialsNonExpired;
	// }
	//
	// @Override
	// public boolean isEnabled() {
	// return this.isEnabled;
	// }
}