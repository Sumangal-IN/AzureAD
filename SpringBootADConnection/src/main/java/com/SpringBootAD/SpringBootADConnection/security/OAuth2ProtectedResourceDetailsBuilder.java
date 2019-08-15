package com.SpringBootAD.SpringBootADConnection.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ProtectedResourceDetailsBuilder {

	@Autowired
	OidcConfigPropertyBuilder oidcConfigPropertyBuilder;

	public OAuth2ProtectedResourceDetails build() {
		final AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
		enrich(authorizationCodeResourceDetails, oidcConfigPropertyBuilder.build());
		return authorizationCodeResourceDetails;
	}

	private static void enrich(final AuthorizationCodeResourceDetails authorizationCodeResourceDetails,
			final OidcConfigProperty oidcConfigProperty) {
		System.out.println(oidcConfigProperty);
		authorizationCodeResourceDetails.setClientId(oidcConfigProperty.getClientID());
		authorizationCodeResourceDetails.setClientSecret(oidcConfigProperty.getClientSecret());
		authorizationCodeResourceDetails.setAccessTokenUri(oidcConfigProperty.getAccessTokenURL());
		authorizationCodeResourceDetails.setUserAuthorizationUri(oidcConfigProperty.getUserAuthorizationURL());
		authorizationCodeResourceDetails.setScope(oidcConfigProperty.getScopes());
		authorizationCodeResourceDetails.setPreEstablishedRedirectUri(oidcConfigProperty.getRedirectURL());
		authorizationCodeResourceDetails.setUseCurrentUri(ApplicationConstant.FALSE);
	}

}