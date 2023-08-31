package com.dream.message.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.message.convert.SmsPlatformConvert;
import com.dream.message.entity.SmsPlatformEntity;
import com.dream.message.query.SmsPlatformQuery;
import com.dream.message.service.SmsPlatformService;
import com.dream.message.sms.SmsContext;
import com.dream.message.sms.config.SmsConfig;
import com.dream.message.sms.service.SmsService;
import com.dream.message.vo.SmsPlatformVO;
import com.dream.message.vo.SmsSendVO;
import com.wy.lang.StrHelper;
import com.wy.result.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 短信平台API
 *
 * @author 飞花梦影
 * @date 2023-07-11 17:19:39
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@RestController
@RequestMapping("message/sms/platform")
@Tag(name = "短信平台API")
public class SmsPlatformController {

	@Autowired
	private SmsPlatformService smsPlatformService;

	@Autowired
	private SmsService smsService;

	@GetMapping("page")
	@Operation(summary = "分页")
	@PreAuthorize("hasAuthority('sms:platform:page')")
	public Result<?> page(@ParameterObject @Valid SmsPlatformQuery query) {
		return smsPlatformService.page(query);
	}

	@GetMapping("{id}")
	@Operation(summary = "信息")
	@PreAuthorize("hasAuthority('sms:platform:info')")
	public Result<SmsPlatformVO> get(@PathVariable("id") Long id) {
		SmsPlatformEntity entity = smsPlatformService.getById(id);
		return Result.ok(SmsPlatformConvert.INSTANCE.convertt(entity));
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
		if (StrHelper.isNotBlank(vo.getParamValue())) {
			params.put(vo.getParamKey(), vo.getParamValue());
		}

		try {
			// 发送短信
			new SmsContext(config).send(vo.getMobile(), params);
			smsService.saveLog(config, vo.getMobile(), params, null);
			return Result.ok();
		} catch (Exception e) {
			smsService.saveLog(config, vo.getMobile(), params, e);
			return Result.error(e.getMessage());
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