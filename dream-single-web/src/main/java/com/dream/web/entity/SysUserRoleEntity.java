package com.dream.web.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.electric.framework.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关系
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class SysUserRoleEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 用户ID
	 */
	private Long userId;

}