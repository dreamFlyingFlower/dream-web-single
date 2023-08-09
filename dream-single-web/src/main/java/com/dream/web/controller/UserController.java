package com.dream.web.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dream.basic.web.controller.AbstractController;
import com.dream.web.entity.UserEntity;
import com.dream.web.helper.SecurityHelper;
import com.dream.web.query.UserQuery;
import com.dream.web.security.SecurityUserDetails;
import com.dream.web.service.UserService;
import com.dream.web.vo.UserPasswordVO;
import com.dream.web.vo.UserVO;
import com.wy.result.Result;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 用户API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Api(tags = "用户API")
@Tag(name = "用户")
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController extends AbstractController<UserEntity, UserVO, UserQuery, UserService> {

	@GetMapping("export")
	@Operation(summary = "导出用户")
	public void export() {
		baseService.export();
	}

	@PostMapping("import")
	@Operation(summary = "导入用户")
	public Result<String> importExcel(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return error("请选择需要上传的文件");
		}
		baseService.importByExcel(file);
		return ok();
	}

	@GetMapping("loginInfo")
	@Operation(summary = "登录用户")
	public Result<SecurityUserDetails> info() {
		return ok(SecurityHelper.getUser());
	}

	@PutMapping("password")
	@Operation(summary = "修改密码")
	public Result<String> password(@RequestBody @Valid UserPasswordVO vo) {
		// 修改密码
		baseService.updatePassword(vo);
		return ok();
	}
}