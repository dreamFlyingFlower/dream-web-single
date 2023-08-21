package com.dream.system.config;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;

import com.wy.collection.ListTool;

import dream.flying.flower.autoconfigure.web.properties.OpenApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;

/**
 * OpenOpi添加全局请求参数
 *
 * @author 飞花梦影
 * @date 2023-08-14 10:21:31
 * @git {@link https://gitee.com/dreamFlyingFlower}
 */
@Configuration
@EnableConfigurationProperties(OpenApiProperties.class)
@AllArgsConstructor
public class OpenApiConfig implements GlobalOperationCustomizer {

	private final OpenApiProperties openApiProperties;

	@Bean
	@ConditionalOnMissingBean
	OpenAPI openApi() {
		return new OpenAPI().info(Optional.ofNullable(openApiProperties.getInfo()).orElse(new Info()))
				.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
				.components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
						.name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")));
	}

	/**
	 * 添加全局参数
	 *
	 * @param operation 操作
	 * @param handlerMethod 方法
	 * @return 操作
	 */
	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {
		if (ListTool.isEmpty(openApiProperties.getGlobalParameters())) {
			return operation;
		}

		List<Parameter> globalParameters = openApiProperties.getGlobalParameters();
		for (Parameter globalParameter : globalParameters) {
			operation.addParametersItem(globalParameter);
		}

		return operation;
	}
}