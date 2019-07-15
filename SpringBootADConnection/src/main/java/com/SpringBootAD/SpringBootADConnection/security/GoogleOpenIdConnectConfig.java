package com.SpringBootAD.SpringBootADConnection.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class GoogleOpenIdConnectConfig {

	private String clientId = "8058f7e8-bc6e-429e-8a81-8e9d5124c951";
	private String clientSecret = ":x]66a3w_/5IBHrDi.jHGzP3bRn+49-:";
	private String accessTokenUri = "https://login.microsoftonline.com/7bf48208-772c-4374-b406-73e86f6127d3/oauth2/v2.0/token";
	private String userAuthorizationUri = "https://login.microsoftonline.com/7bf48208-772c-4374-b406-73e86f6127d3/oauth2/v2.0/authorize";
	private String redirectUri = "https://e04a4ade.ngrok.io/mylogin";

	@Bean
	public OAuth2ProtectedResourceDetails googleOpenId() {
		final AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setClientId(clientId);
		details.setClientSecret(clientSecret);
		details.setAccessTokenUri(accessTokenUri);
		details.setUserAuthorizationUri(userAuthorizationUri);
		details.setScope(Arrays.asList("openid", "email", "profile", "offline_access"));
		details.setPreEstablishedRedirectUri(redirectUri);
		details.setUseCurrentUri(false);
		return details;
	}

	@Bean
	public OAuth2RestTemplate googleOpenIdTemplate(final OAuth2ClientContext clientContext) {
		final OAuth2RestTemplate template = new OAuth2RestTemplate(googleOpenId(), clientContext);
		return template;
	}

}