package com.kingfisher.agile.auth.client.security.builder;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.security.model.OidcProperty;

@Component
public class OidcConfigPropertyBuilder {

	@Value("${auth.azure.clientID}")
	private String clientID;

	@Value("${auth.azure.clientSecret}")
	private String clientSecret;

	@Value("${auth.azure.redirectUrlHost}")
	private String redirectUrlHost;

	@Value("${auth.azure.tenantID}")
	private String tenantID;

	@Value("${auth.azure.scopes}")
	private String[] scopes;

	@Autowired
	OidcConfigPropertyBuilder oidcConfigProviderBuilder;

	public OidcProperty build() {
		return OidcProperty.builder().clientID(oidcConfigProviderBuilder.clientID)
				.clientSecret(oidcConfigProviderBuilder.clientSecret)
				.redirectURL(redirectUrlBuilder.build(oidcConfigProviderBuilder.redirectUrlHost))
				.userAuthorizationURL(UserAuthorizationUrlBuilder.build(oidcConfigProviderBuilder.tenantID))
				.accessTokenURL(AccessTokenUrlBuilder.build(oidcConfigProviderBuilder.tenantID))
				.scopes(Arrays.asList(oidcConfigProviderBuilder.scopes).stream().map(x -> x.toString())
						.collect(Collectors.toList()))
				.build();
	}

	static class redirectUrlBuilder {
		public static String build(String redirectUrlHost) {
			return redirectUrlHost.concat(ApplicationConstant.AUTH_CONTEXT_PATH_REPLY);
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
