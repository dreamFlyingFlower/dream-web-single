package com.dream.monitor.vo;

import java.util.LinkedList;
import java.util.List;

import com.dream.monitor.entity.CpuInfo;
import com.dream.monitor.entity.DiskInfo;
import com.dream.monitor.entity.JvmInfo;
import com.dream.monitor.entity.MemoryInfo;
import com.wy.lang.NumberHelper;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

/**
 * Server Info
 *
 * @author Pure tea
 */
@Tag(name = "服务器信息")
@Data
@Slf4j
public class Server {

	/**
	 * Cpu Info
	 */
	@Schema(description = "CPU信息")
	private CpuInfo cpu;

	/**
	 * Mem Info
	 */
	@Schema(description = "内存信息")
	private MemoryInfo mem;

	/**
	 * Jvm Info
	 */
	@Schema(description = "JVM信息")
	private JvmInfo jvm;

	/**
	 * Sys Info
	 */
	@Schema(description = "系统信息")
	private SystemInfo sys;

	/**
	 * SysFile Info
	 */
	@Schema(description = "系统文件信息")
	private List<DiskInfo> disks = new LinkedList<>();

	public Server() {
		this.cpu = new CpuInfo();
		this.mem = new MemoryInfo();
		this.jvm = new JvmInfo();
		this.sys = new SystemInfo();
		this.setDiskList();
		log.info("Server Info => {}", this);
	}

	public Server(DiskInfo disk) {
		this.setDiskList();
		log.info("Server Info => {}", this);
	}

	/**
	 * 设置磁盘信息
	 */
	private void setDiskList() {
		SystemInfo systemInfo = new SystemInfo();
		FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
		List<OSFileStore> fsArray = fileSystem.getFileStores();
		for (OSFileStore fs : fsArray) {
			long free = fs.getUsableSpace();
			long total = fs.getTotalSpace();
			long used = total - free;
			DiskInfo disk = new DiskInfo();
			disk.setDiskName(fs.getName());
			disk.setDiskType(fs.getType());
			disk.setDirName(fs.getMount());
			disk.setTotal(convertFileSize(total));
			disk.setFree(convertFileSize(free));
			disk.setUsed(convertFileSize(used));
			disk.setUsage(NumberHelper.multiply(NumberHelper.div(used, total, 4), 100).doubleValue());
			this.disks.add(disk);
		}
	}

	/**
	 * 字节转换
	 *
	 * @param size 字节大小
	 * @return 转换后值
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else {
			return String.format("%d B", size);
		}
	}

}