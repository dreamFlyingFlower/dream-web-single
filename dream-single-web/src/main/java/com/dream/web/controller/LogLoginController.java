package com.dream.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.web.service.LogLoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 登录日志
 * 
 * @author 飞花梦影
 * @date 2023-08-09 15:41:37
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("sys/log/login")
@Tag(name = "登录日志")
@AllArgsConstructor
public class LogLoginController {

	private final LogLoginService logLoginService;

	@GetMapping("export")
	@Operation(summary = "导出excel")
	@PreAuthorize("hasAuthority('sys:log:login')")
	public void export() {
		logLoginService.export();
	}
}