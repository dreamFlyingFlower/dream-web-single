package com.electric.monitor.model;

import lombok.Data;

/**
 * 硬盘
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:07:22
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
public class Disk {

	/**
	 * 磁盘名称
	 */
	private String diskName;

	/**
	 * 磁盘类型
	 */
	private String diskType;

	/**
	 * 磁盘路径
	 */
	private String dirName;

	/**
	 * 总大小
	 */
	private String total;

	/**
	 * 剩余大小
	 */
	private String free;

	/**
	 * 已经使用量
	 */
	private String used;

	/**
	 * 资源的使用率
	 */
	private double usage;
}