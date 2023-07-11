package com.dream.monitor.model;

import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.OshiUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * System Info
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:08:24
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@Builder
@AllArgsConstructor
public class SystemInfo {

	/**
	 * 操作系统
	 */
	private String osName;

	/**
	 * 系统架构
	 */
	private String osArch;

	/**
	 * 系统版本
	 */
	private String osVersion;

	/**
	 * 服务器名称
	 */
	private String computerName;

	/**
	 * 服务器Ip
	 */
	private String computerIp;

	public SystemInfo() {
		this.setOsName(SystemUtil.getOsInfo().getName());
		this.setOsArch(SystemUtil.getOsInfo().getArch());
		this.setOsVersion(SystemUtil.getOsInfo().getVersion());
		this.setComputerName(OshiUtil.getOs().getNetworkParams().getHostName());
		this.setComputerIp(SystemUtil.getHostInfo().getAddress());
	}
}