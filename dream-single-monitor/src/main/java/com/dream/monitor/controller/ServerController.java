package com.dream.monitor.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.monitor.entity.CpuInfo;
import com.dream.monitor.entity.DiskInfo;
import com.dream.monitor.entity.JvmInfo;
import com.dream.monitor.entity.MemoryInfo;
import com.dream.monitor.entity.SystemInfo;
import com.dream.monitor.vo.Server;
import com.wy.result.Result;

/**
 * 服务器监控
 *
 * @author 飞花梦影
 * @date 2023-07-10 17:58:48
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("monitor/server")
public class ServerController {

	/**
	 * 服务器相关信息
	 */
	@GetMapping("info")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<Server> getServerInfo() {
		Server server = new Server();
		return Result.ok(server);
	}

	/**
	 * CPU相关信息
	 */
	@GetMapping("cpu")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<CpuInfo> getCpuInfo() {
		CpuInfo cpu = new CpuInfo();
		return Result.ok(cpu);
	}

	/**
	 * 内存相关信息
	 */
	@GetMapping("mem")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<MemoryInfo> getMemInfo() {
		MemoryInfo mem = new MemoryInfo();
		return Result.ok(mem);
	}

	/**
	 * JVM相关信息
	 */
	@GetMapping("jvm")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<JvmInfo> getJvmInfo() {
		JvmInfo jvm = new JvmInfo();
		return Result.ok(jvm);
	}

	/**
	 * 系统相关信息
	 */
	@GetMapping("sys")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<SystemInfo> getSysInfo() {
		SystemInfo sys = new SystemInfo();
		return Result.ok(sys);
	}

	/**
	 * 系统文件相关信息
	 */
	@GetMapping("disk")
	@PreAuthorize("hasAuthority('monitor:server:all')")
	public Result<List<DiskInfo>> getSysFileInfo() {
		Server server = new Server(new DiskInfo());
		return Result.ok(server.getDisks());
	}
}