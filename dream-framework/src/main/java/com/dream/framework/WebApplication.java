package com.dream.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 启动类
 * 
 * @author 飞花梦影
 * @date 2022-06-17 14:36:16
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.dream.web.mapper")
@EnableOpenApi
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}