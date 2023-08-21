package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wy.annotation.Unique;

import dream.framework.mybatis.plus.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 组织机构
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "组织机构")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_org")
public class OrgEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 组织机构编码
	 */
	@Schema(description = "组织机构编码")
	@Unique
	private String orgCode;

	/**
	 * 组织机构简称
	 */
	@Schema(description = "组织机构简称")
	private String orgName;

	/**
	 * 上级组织机构,顶层组织机构为0
	 */
	@Schema(description = "上级组织机构,顶层组织机构为0")
	private Long pid;

	/**
	 * 组织机构层级,由高到低,以-相连
	 */
	@Schema(description = "组织机构层级,由高到低,以-相连")
	private String level;
}