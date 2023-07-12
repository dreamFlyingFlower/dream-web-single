package com.dream.framework.utils;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

public abstract class GuidGenerator {

	private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);

	private GuidGenerator() {
	}

	public static String generateClientSecret() {
		return defaultClientSecretGenerator.generate();
	}
}