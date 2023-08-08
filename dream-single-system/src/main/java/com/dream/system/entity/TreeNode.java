package com.dream.system.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.dream.basic.web.valid.ValidEdit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 树节点,所有需要实现树节点的,都可以继承该类
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:42:26
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(description = "id")
	@NotNull(groups = ValidEdit.class)
	private Long id;

	/**
	 * 上级组织机构,顶层组织机构为0
	 */
	@Schema(description = "上级组织机构,顶层组织机构为0")
	@NotNull(message = "上级ID不能为空")
	private Long pid;

	/**
	 * 子节点列表
	 */
	private List<T> children;
}