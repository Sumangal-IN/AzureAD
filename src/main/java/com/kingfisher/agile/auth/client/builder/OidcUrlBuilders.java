package com.kingfisher.agile.auth.client.builder;

import static java.lang.String.format;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;

public class OidcUrlBuilders {

	static class RedirectUrlBuilder {
		public static String build(String redirectUrlHost) {
			return redirectUrlHost.concat(
					ApplicationConstant.CONTEXT_PATH_SEPARATOR.concat(ApplicationConstant.CONTEXT_PATH_AUTH_REPLY));
		}
	}

	static class UserAuthorizationUrlBuilder {
		public static String build(String tenantID) {
			return format(ApplicationConstant.AUTHORIZATION_URL, tenantID);
		}
	}

	static class AccessTokenUrlBuilder {
		public static String build(String tenantID) {
			return format(ApplicationConstant.ACCESS_TOKEN_URL, tenantID);
		}
	}
}
