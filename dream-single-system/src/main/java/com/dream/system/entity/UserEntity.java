package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;
import com.dream.framework.enums.UserStatus;
import com.wy.annotation.Unique;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "用户")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class UserEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编码
	 */
	private String userCode;

	/**
	 * 用户名,唯一
	 */
	@Unique
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 姓名
	 */
	private String realName;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 性别:1-男;2-女;3-未知
	 */
	private Integer gender;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 部门ID
	 */
	private Long departId;

	/**
	 * 组织机构ID
	 */
	private Long orgId;

	/**
	 * 超级管理员 0：否 1：是
	 */
	private Integer superAdmin;

	/**
	 * 岗位证书
	 */
	private String userCert;

	/**
	 * 用户状态,见字典user_status,{@link UserStatus}
	 */
	private Integer status;
}