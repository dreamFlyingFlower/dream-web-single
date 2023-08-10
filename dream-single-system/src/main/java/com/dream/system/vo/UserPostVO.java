package com.dream.system.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户岗位关系VO
 * 
 * @auther 飞花梦影
 * @date 2022-08-31 15:07:49
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Schema(description = "用户岗位关系VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostVO implements Serializable {

	private static final long serialVersionUID = 5144501583392985220L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 岗位ID
	 */
	private Long postId;
}