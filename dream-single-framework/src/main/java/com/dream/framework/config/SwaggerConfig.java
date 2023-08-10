//package com.dream.framework.config;
//
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//
//@Configuration
//public class SwaggerConfig {
//
//	@Bean
//	GroupedOpenApi groupedOpenApi() {
//		String[] paths = { "/**" };
//		String[] packagedToMatch = { "com.dream.web.controller" };
//		return GroupedOpenApi.builder().group("业务管理").pathsToMatch(paths).packagesToScan(packagedToMatch).build();
//	}
//
//	@Bean
//	OpenAPI customOpenAPI() {
//		Contact contact = new Contact();
//		contact.setName("飞花梦影");
//		contact.setEmail("582822832@qq.com");
//		return new OpenAPI().info(new Info().title("单机版系统管理API").description("单机版系统管理API").contact(contact)
//				.version("1.0").license(new License().name("MIT")));
//	}
//}