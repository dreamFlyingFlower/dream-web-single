package com.dream.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import dream.framework.web.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 附件管理表 ts_attachment
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:01:27
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "按钮表 ts_button")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_attachment")
public class AttachmentEntity extends AbstractEntity {

	private static final long serialVersionUID = -4083079108816931702L;

	/**
	 * 附件名称
	 */
	private String name;

	/**
	 * 附件地址
	 */
	private String url;

	/**
	 * 附件大小
	 */
	private Long size;

	/**
	 * 存储平台
	 */
	private String platform;
}