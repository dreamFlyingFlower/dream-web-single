package com.dream.system.web.query;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 附件管理查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@ApiModel(description = "附件管理查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentQuery extends AbstractQuery {

	private static final long serialVersionUID = -540854032716623343L;

	@Schema(description = "附件名称")
	private String name;

	@Schema(description = "存储平台")
	private String platform;

}