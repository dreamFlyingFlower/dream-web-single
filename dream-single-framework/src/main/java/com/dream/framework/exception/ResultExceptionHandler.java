package com.dream.framework.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wy.enums.TipEnum;
import com.wy.result.Result;
import com.wy.result.ResultException;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 *
 * @author 飞花梦影
 * @date 2023-08-10 14:38:30
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Slf4j
@RestControllerAdvice
public class ResultExceptionHandler {

	@ExceptionHandler(ResultException.class)
	public Result<String> handleException(ResultException ex) {
		ex.printStackTrace();
		return Result.error(ex.getCode(), ex.getMessage());
	}

	/**
	 * SpringMVC参数绑定,Validator校验不正确
	 */
	@ExceptionHandler(BindException.class)
	public Result<String> bindException(BindException ex) {
		ex.printStackTrace();
		FieldError fieldError = ex.getFieldError();
		assert fieldError != null;
		return Result.error(fieldError.getDefaultMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public Result<String> handleAccessDeniedException(Exception ex) {
		ex.printStackTrace();
		return Result.error(TipEnum.TIP_INTERNAL_UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public Result<String> handleException(Exception ex) {
		ex.printStackTrace();
		log.error(ex.getMessage(), ex);
		return Result.error(TipEnum.TIP_INTERNAL_SERVER_ERROR);
	}
}