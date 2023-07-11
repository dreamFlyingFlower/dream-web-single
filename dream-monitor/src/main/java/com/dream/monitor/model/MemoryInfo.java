package com.dream.monitor.model;

import com.dream.monitor.utils.ArityUtil;

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
		this.setTotal(ArityUtil.div(globalMemory.getTotal(), 1024 * 1024 * 1024, 2));
		this.setFree(ArityUtil.div(globalMemory.getAvailable(), 1024 * 1024 * 1024, 2));
		this.setUsed(ArityUtil.sub(this.getTotal(), this.getFree()));
		this.setUsage(ArityUtil.round(ArityUtil.div(this.getUsed(), this.getTotal(), 4) * 100, 2));
	}
}