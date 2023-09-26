package com.dream.message.controller;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.message.convert.SmsLogConvert;
import com.dream.message.entity.SmsLogEntity;
import com.dream.message.query.SmsLogQuery;
import com.dream.message.service.SmsLogService;
import com.dream.message.vo.SmsLogVO;
import com.wy.result.Result;

import dream.framework.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 短信日志
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:09:51
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("message/sms/log")
@Tag(name = "短信日志")
@AllArgsConstructor
public class SmsLogController implements BaseController {

	private final SmsLogService smsLogService;

	@GetMapping("page")
	@Operation(summary = "分页")
	@PreAuthorize("hasAuthority('sms:log')")
	public Result<?> page(@ParameterObject @Valid SmsLogQuery query) {
		return smsLogService.listPage(query);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	@PreAuthorize("hasAuthority('sms:log')")
	public Result<SmsLogVO> get(@PathVariable("id") Long id) {
		SmsLogEntity entity = smsLogService.getById(id);
		return Result.ok(SmsLogConvert.INSTANCE.convertt(entity));
	}
}