package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dream.basic.web.entity.AbstractEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 用户岗位关系
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "用户岗位关系")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_post")
public class UserPostEntity extends AbstractEntity {

	private static final long serialVersionUID = -6638468804336609656L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 岗位ID
	 */
	private Long postId;
}