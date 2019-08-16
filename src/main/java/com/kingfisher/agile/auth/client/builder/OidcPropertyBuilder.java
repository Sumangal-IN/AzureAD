package com.kingfisher.agile.auth.client.builder;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.kingfisher.agile.auth.client.builder.UrlBuilders.buildAccessTokenURL;
import static com.kingfisher.agile.auth.client.builder.UrlBuilders.buildRedirectURL;
import static com.kingfisher.agile.auth.client.builder.UrlBuilders.buildUserAuthorizationURL;
import com.kingfisher.agile.auth.client.model.OidcProperty;;

@Component
public class OidcPropertyBuilder {

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
	OidcPropertyBuilder oidcConfigProviderBuilder;

	public OidcProperty build() {
		return OidcProperty.builder().clientID(oidcConfigProviderBuilder.clientID)
				.clientSecret(oidcConfigProviderBuilder.clientSecret)
				.redirectURL(buildRedirectURL(oidcConfigProviderBuilder.redirectUrlHost))
				.userAuthorizationURL(buildUserAuthorizationURL(oidcConfigProviderBuilder.tenantID))
				.accessTokenURL(buildAccessTokenURL(oidcConfigProviderBuilder.tenantID))
				.scopes(Arrays.asList(oidcConfigProviderBuilder.scopes).stream().map(x -> x.toString())
						.collect(Collectors.toList()))
				.build();
	}

}
