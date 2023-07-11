package com.dream.monitor.model;

import cn.hutool.system.oshi.OshiUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * CPU信息
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:07:00
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@Builder
@AllArgsConstructor
public class CpuInfo {

	/**
	 * 设置等待时间,单位毫秒
	 */
	private static final Long JOSHI_WAIT_SECOND = 360L;

	/**
	 * CPU型号
	 */
	private String cpuModel;

	/**
	 * 核心数
	 */
	private int cpuNum;

	/**
	 * CPU总的使用率
	 */
	private double total;

	/**
	 * CPU系统使用率
	 */
	private double sys;

	/**
	 * CPU用户使用率
	 */
	private double used;

	/**
	 * CPU当前等待率
	 */
	private double wait;

	/**
	 * CPU当前空闲率
	 */
	private double free;

	public CpuInfo() {
		// 获取CPU相关信息,间隔1秒
		cn.hutool.system.oshi.CpuInfo cpuInfo = OshiUtil.getCpuInfo(JOSHI_WAIT_SECOND);
		this.setCpuModel(cpuInfo.getCpuModel().split("\n")[0]);
		this.setCpuNum(cpuInfo.getCpuNum());
		this.setTotal(cpuInfo.getToTal());
		this.setSys(cpuInfo.getSys());
		this.setUsed(cpuInfo.getUser());
		this.setWait(cpuInfo.getWait());
		this.setFree(cpuInfo.getFree());
	}
}