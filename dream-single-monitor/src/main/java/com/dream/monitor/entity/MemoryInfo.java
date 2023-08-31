package com.dream.monitor.entity;

import com.wy.lang.NumberHelper;

import cn.hutool.system.oshi.OshiUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import oshi.hardware.GlobalMemory;

/**
 * 内存
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:07:56
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@Builder
@AllArgsConstructor
public class MemoryInfo {

	/**
	 * 内存总数(G)
	 */
	private double total;

	/**
	 * 已用内存(G)
	 */
	private double used;

	/**
	 * 剩余内存(G)
	 */
	private double free;

	/**
	 * 内存使用率
	 */
	private double usage;

	public MemoryInfo() {
		GlobalMemory globalMemory = OshiUtil.getMemory();
		this.setTotal(NumberHelper.div(globalMemory.getTotal(), 1024 * 1024 * 1024).doubleValue());
		this.setFree(NumberHelper.div(globalMemory.getAvailable(), 1024 * 1024 * 1024).doubleValue());
		this.setUsed(NumberHelper.subtract(this.getTotal(), this.getFree()).doubleValue());
		this.setUsage(NumberHelper.round(NumberHelper.div(this.getUsed(), this.getTotal(), 4).doubleValue() * 100, 2));
	}
}