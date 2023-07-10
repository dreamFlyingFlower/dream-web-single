package com.electric.message.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import com.electric.framework.page.PageResult;
import com.electric.framework.utils.ExceptionUtils;
import com.electric.framework.utils.Result;
import com.electric.message.convert.SmsPlatformConvert;
import com.electric.message.entity.SmsPlatformEntity;
import com.electric.message.query.SmsPlatformQuery;
import com.electric.message.service.SmsPlatformService;
import com.electric.message.sms.SmsContext;
import com.electric.message.sms.config.SmsConfig;
import com.electric.message.sms.service.SmsService;
import com.electric.message.vo.SmsPlatformVO;
import com.electric.message.vo.SmsSendVO;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信平台
 *
 * @author  
 */
@RestController
@RequestMapping("message/sms/platform")
@Tag(name = "短信平台")
@AllArgsConstructor
public class SmsPlatformController {
    private final SmsPlatformService smsPlatformService;
    private final SmsService smsService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sms:platform:page')")
    public Result<PageResult<SmsPlatformVO>> page(@ParameterObject @Valid SmsPlatformQuery query) {
        PageResult<SmsPlatformVO> page = smsPlatformService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sms:platform:info')")
    public Result<SmsPlatformVO> get(@PathVariable("id") Long id) {
        SmsPlatformEntity entity = smsPlatformService.getById(id);

        return Result.ok(SmsPlatformConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sms:platform:save')")
    public Result<String> save(@RequestBody SmsPlatformVO vo) {
        smsPlatformService.save(vo);

        return Result.ok();
    }

    @PostMapping("send")
    @Operation(summary = "发送短信")
    @PreAuthorize("hasAuthority('sms:platform:update')")
    public Result<String> send(@RequestBody SmsSendVO vo) {
        SmsPlatformEntity entity = smsPlatformService.getById(vo.getId());
        SmsConfig config = SmsPlatformConvert.INSTANCE.convert2(entity);

        // 短信参数
        Map<String, String> params = new LinkedHashMap<>();
        if (StringUtils.isNotBlank(vo.getParamValue())) {
            params.put(vo.getParamKey(), vo.getParamValue());
        }

        try {
            // 发送短信
            new SmsContext(config).send(vo.getMobile(), params);

            // 保存日志
            smsService.saveLog(config, vo.getMobile(), params, null);

            return Result.ok();
        } catch (Exception e) {
            // 保存日志
            smsService.saveLog(config, vo.getMobile(), params, e);

            return Result.error(ExceptionUtils.getExceptionMessage(e));
        }
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sms:platform:update')")
    public Result<String> update(@RequestBody @Valid SmsPlatformVO vo) {
        smsPlatformService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sms:platform:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        smsPlatformService.delete(idList);

        return Result.ok();
    }
}