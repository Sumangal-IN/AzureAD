package com.SpringBootAD.SpringBootADConnection.security;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OidcConfigPropertyBuilder {

	@Value("${auth.azure.clientID}")
	private String clientID;

	@Value("${auth.azure.clientSecret}")
	private String clientSecret;

	@Value("${auth.azure.redirectURL}")
	private String redirectURL;

	@Value("${auth.azure.tenantID}")
	private String tenantID;

	@Value("${auth.azure.scopes}")
	private String[] scopes;

	@Autowired
	OidcConfigPropertyBuilder oidcConfigProviderBuilder;

	public OidcConfigProperty build() {
		return OidcConfigProperty.builder().clientID(oidcConfigProviderBuilder.clientID)
				.clientSecret(oidcConfigProviderBuilder.clientSecret).redirectURL(oidcConfigProviderBuilder.redirectURL)
				.userAuthorizationURL(UserAuthorizationURL.build(oidcConfigProviderBuilder.tenantID))
				.accessTokenURL(AccessTokenURL.build(oidcConfigProviderBuilder.tenantID))
				.scopes(Arrays.asList(oidcConfigProviderBuilder.scopes).stream().map(x -> x.toString())
						.collect(Collectors.toList()))
				.build();
	}

	static class UserAuthorizationURL {
		public static String build(String tenantID) {
			return format(ApplicationConstant.AUTHORIZATION_URL, tenantID);
		}
	}

	static class AccessTokenURL {
		public static String build(String tenantID) {
			return format(ApplicationConstant.ACCESS_TOKEN_URL, tenantID);
		}
	}

}
