package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.electric.framework.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关系
 *
 * @author  
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_role_menu")
public class SysRoleMenuEntity extends BaseEntity {
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 菜单ID
	 */
	private Long menuId;

}