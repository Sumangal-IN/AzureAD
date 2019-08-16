package com.kingfisher.agile.auth.client.builder;

import static java.lang.String.format;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;

public class UrlBuilders {

	public static String buildRedirectURL(String buildredirectUrlHost) {
		return buildredirectUrlHost
				.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR.concat(ApplicationConstant.CONTEXT_PATH_AUTH_REPLY));
	}

	public static String buildUserAuthorizationURL(String tenantID) {
		return format(ApplicationConstant.AUTHORIZATION_URL, tenantID);
	}

	public static String buildAccessTokenURL(String tenantID) {
		return format(ApplicationConstant.ACCESS_TOKEN_URL, tenantID);
	}

}
