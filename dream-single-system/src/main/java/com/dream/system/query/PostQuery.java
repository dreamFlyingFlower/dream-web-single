package com.dream.system.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 岗位管理查询
 *
 * @author 飞花梦影
 * @date 2023-08-07 17:09:03
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "岗位管理查询")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostQuery extends AbstractQuery {

	private static final long serialVersionUID = 7686583533544978507L;

	@Schema(description = "岗位编码")
	private String postCode;

	@Schema(description = "岗位名称")
	private String postName;

	@Schema(description = "状态  0：停用   1：正常")
	private Integer status;
}