package com.dream.system.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;

import dream.framework.web.easyexcel.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel用户表
 *
 * @author 飞花梦影
 * @date 2023-08-08 09:51:37
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExcelVO implements Serializable, TransPojo {

	private static final long serialVersionUID = 1L;

	/**
	 * 本属性对于导出无用，只是用于翻译
	 */
	@ExcelIgnore
	private Long id;

	@ExcelProperty("用户名")
	private String username;

	@ExcelProperty("姓名")
	private String realName;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "user_gender", ref = "genderLabel")
	private Integer gender;

	@ExcelProperty(value = "性别")
	private String genderLabel;

	@ExcelProperty("邮箱")
	private String email;

	@ExcelProperty("手机号")
	private String mobile;

	@ExcelProperty("机构ID")
	private Long orgId;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "user_status", ref = "statusLabel")
	private Integer status;

	@ExcelProperty(value = "状态")
	private String statusLabel;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "user_super_admin", ref = "superAdminLabel")
	private Integer superAdmin;

	@ExcelProperty(value = "超级管理员")
	private String superAdminLabel;

	@ExcelProperty(value = "创建时间", converter = DateConverter.class)
	private Date createTime;
}