package com.dream.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.dream.system.excel.DateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import com.wy.ConstDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录日志
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:25:28
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Schema(description = "登录日志")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogLoginVO implements Serializable, TransPojo {

	private static final long serialVersionUID = 1L;

	@ExcelIgnore
	@Schema(description = "id")
	private Long id;

	@ExcelProperty("用户名")
	@Schema(description = "用户名")
	private String username;

	@ExcelProperty("登录IP")
	@Schema(description = "登录IP")
	private String ip;

	@ExcelProperty("登录地点")
	@Schema(description = "登录地点")
	private String address;

	@ExcelProperty("User Agent")
	@Schema(description = "User Agent")
	private String userAgent;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "success_fail", ref = "statusLabel")
	@Schema(description = "登录状态  0：失败   1：成功")
	private Integer status;

	@ExcelProperty(value = "登录状态")
	private String statusLabel;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "login_operation", ref = "operationLabel")
	@Schema(description = "操作信息   0：登录成功   1：退出成功  2：验证码错误  3：账号密码错误")
	private Integer operation;

	@ExcelProperty(value = "操作信息")
	private String operationLabel;

	@ExcelProperty(value = "创建时间", converter = DateConverter.class)
	@Schema(description = "创建时间")
	@JsonFormat(pattern = ConstDate.DATETIME)
	private Date createTime;
}