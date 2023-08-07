package com.dream.system.web.vo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dream.basic.web.valid.ValidEdit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表DTO
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:10
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "用户表DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements UserDetails {

	private static final long serialVersionUID = 2195467941855219248L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 用户名,唯一
	 */
	@ApiModelProperty("用户名,唯一")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;

	/**
	 * 盐
	 */
	@ApiModelProperty("盐")
	private String salt;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	private String realName;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String nickname;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	private String avatar;

	/**
	 * 性别
	 */
	@ApiModelProperty("性别")
	private Integer gender;

	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;

	/**
	 * 超级管理员:1是,0否
	 */
	@ApiModelProperty("超级管理员:1是,0否")
	private Integer superAdmin;

	/**
	 * 部门ID
	 */
	@ApiModelProperty("部门ID")
	private Long departId;

	/**
	 * 组织机构ID
	 */
	@ApiModelProperty("组织机构ID")
	private Long orgId;

	/**
	 * 用户状态,见字典user_status
	 */
	@ApiModelProperty("用户状态,见字典user_status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@ApiModelProperty("创建人ID")
	private Long createUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

	/**
	 * 更新人ID
	 */
	@ApiModelProperty("更新人ID")
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

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authoritySet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
}