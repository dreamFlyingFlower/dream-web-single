package com.electric.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import com.electric.framework.page.PageResult;
import com.electric.framework.utils.Result;
import com.electric.message.convert.SmsLogConvert;
import com.electric.message.entity.SmsLogEntity;
import com.electric.message.query.SmsLogQuery;
import com.electric.message.service.SmsLogService;
import com.electric.message.vo.SmsLogVO;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 短信日志
 *
 * @author  
 */
@RestController
@RequestMapping("message/sms/log")
@Tag(name = "短信日志")
@AllArgsConstructor
public class SmsLogController {
    private final SmsLogService smsLogService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sms:log')")
    public Result<PageResult<SmsLogVO>> page(@ParameterObject @Valid SmsLogQuery query) {
        PageResult<SmsLogVO> page = smsLogService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sms:log')")
    public Result<SmsLogVO> get(@PathVariable("id") Long id) {
        SmsLogEntity entity = smsLogService.getById(id);

        return Result.ok(SmsLogConvert.INSTANCE.convert(entity));
    }

}