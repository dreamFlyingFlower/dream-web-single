package com.dream.web.oauth.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * 自定义OAuth2异常序列化处理器
 * 
 * @author 飞花梦影
 * @date 2022-09-13 17:44:08
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Deprecated
public class OAuth2ExceptionSerializer extends StdSerializer<OAuth2Exception> {

	private static final long serialVersionUID = 1374299379306174503L;

	protected OAuth2ExceptionSerializer() {
		super(OAuth2Exception.class);
	}

	@Override
	public void serialize(OAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("code", value.getHttpErrorCode());
		String errorMessage = value.getOAuth2ErrorCode();
		if (errorMessage != null) {
			errorMessage = HtmlUtils.htmlEscape(errorMessage);
		}
		gen.writeStringField("msg", value.getMessage());
		String summary = value.getSummary();
		gen.writeStringField("data", summary);

		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				gen.writeStringField(key, add);
			}
		}
		gen.writeEndObject();
	}
}