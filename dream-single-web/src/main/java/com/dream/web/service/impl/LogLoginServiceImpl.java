package com.dream.web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.dream.basic.core.excel.EasyExcelHelper;
import com.dream.basic.core.helper.AddressHelper;
import com.dream.basic.core.helper.IpHelper;
import com.dream.basic.core.helper.WebHelper;
import com.dream.basic.web.service.impl.AbstractServiceImpl;
import com.dream.web.convert.LogLoginConvert;
import com.dream.web.entity.LogLoginEntity;
import com.dream.web.mapper.LogLoginMapper;
import com.dream.web.query.LogLoginQuery;
import com.dream.web.service.LogLoginService;
import com.dream.web.vo.LogLoginVO;
import com.fhs.trans.service.impl.TransService;
import com.wy.util.DateTimeTool;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 登录日志
 *
 * @author 飞花梦影
 * @date 2023-08-08 14:51:03
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Service
@AllArgsConstructor
public class LogLoginServiceImpl
		extends AbstractServiceImpl<LogLoginEntity, LogLoginVO, LogLoginQuery, LogLoginConvert, LogLoginMapper>
		implements LogLoginService {

	private final TransService transService;

	@Override
	public void save(String username, Integer status, Integer operation) {
		HttpServletRequest request = WebHelper.getRequest();
		assert request != null;
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = IpHelper.getIp(request);
		String address = AddressHelper.getAddressByIP(ip);
		LogLoginEntity entity = LogLoginEntity.builder().username(username).status(status).operation(operation).ip(ip)
				.address(address).userAgent(userAgent).build();
		baseMapper.insert(entity);
	}

	@Override
	@SneakyThrows
	public void export() {
		List<LogLoginEntity> list = list();
		List<LogLoginVO> sysLogLoginVOS = baseConvert.convertt(list);
		transService.transBatch(sysLogLoginVOS);
		EasyExcelHelper.excelExport(LogLoginVO.class, "system_login_log_excel" + DateTimeTool.formatDateTime(), null,
				sysLogLoginVOS);
	}
}