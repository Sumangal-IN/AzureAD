package com.kingfisher.agile.auth.client.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import com.kingfisher.agile.auth.client.security.builder.OAuth2ProtectedResourceDetailsBuilder;

@Configuration
@EnableOAuth2Client
public class OidcConfig {

	@Autowired
	OAuth2ProtectedResourceDetailsBuilder oAuth2ProtectedResourceDetailsBuilder;

	@Bean
	public OAuth2RestTemplate oidcTemplate(final OAuth2ClientContext clientContext) {
		final OAuth2RestTemplate template = new OAuth2RestTemplate(oAuth2ProtectedResourceDetailsBuilder.build(),
				clientContext);
		new StringHttpMessageConverter();
		return template;
	}

}