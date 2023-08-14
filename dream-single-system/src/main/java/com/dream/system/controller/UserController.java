package com.dream.system.controller;

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
import com.dream.framework.security.user.SecurityHelper;
import com.dream.framework.security.user.SecurityUserDetails;
import com.dream.system.entity.UserEntity;
import com.dream.system.query.UserQuery;
import com.dream.system.service.UserService;
import com.dream.system.vo.UserPasswordVO;
import com.dream.system.vo.UserVO;
import com.wy.result.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 用户管理API
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:58:56
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Tag(name = "用户管理API")
@RestController
@RequestMapping("sys/user")
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