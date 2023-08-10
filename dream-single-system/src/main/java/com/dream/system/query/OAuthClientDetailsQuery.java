package com.dream.system.query;

import java.time.LocalDateTime;

import com.dream.basic.web.query.AbstractQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 按钮表查询参数
 * 
 * @author 飞花梦影
 * @date 2022-09-01 16:09:57
 * @git {@link https://github.com/dreamFlyingFlower}
 */
@Schema(description = "按钮表查询参数")
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthClientDetailsQuery extends AbstractQuery {

	private static final long serialVersionUID = -6947822646185526939L;

	@Builder.Default
	private LocalDateTime createTime = LocalDateTime.now();

	@Builder.Default
	private boolean archived = false;

	private String clientId;

	private String resourceIds;

	private String clientSecret;

	/**
	 * Available values: read,write
	 */
	private String scope;

	/**
	 * grant types include "authorization_code", "password", "assertion", and
	 * "refresh_token". Default value is "authorization_code,refresh_token".
	 */
	@Builder.Default
	private String authorizedGrantTypes = "authorization_code,refresh_token";

	/**
	 * The re-direct URI(s) established during registration (optional, comma
	 * separated).
	 */
	private String webServerRedirectUri;

	/**
	 * Authorities that are granted to the client (comma-separated). Distinct from
	 * the authorities granted to the user on behalf of whom the client is acting.
	 * <p/>
	 * For example: ROLE_USER
	 */
	private String authorities;

	/**
	 * The access token validity period in seconds (optional). If unspecified a
	 * global default will be applied by the token services.
	 */
	private Integer accessTokenValidity;

	/**
	 * The refresh token validity period in seconds (optional). If unspecified a
	 * global default will be applied by the token services.
	 */
	private Integer refreshTokenValidity;

	// optional
	private String additionalInformation;

	/**
	 * The client is trusted or not. If it is trust, will skip approve step default
	 * false.
	 */
	@Builder.Default
	private boolean trusted = false;

	/**
	 * Value is 'true' or 'false', default 'false'
	 */
	private String autoApprove;
}