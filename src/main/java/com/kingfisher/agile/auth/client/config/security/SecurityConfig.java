package com.kingfisher.agile.auth.client.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.filter.LogRequestFilter;
import com.kingfisher.agile.auth.client.filter.OpenIdConnectFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(ApplicationConstant.CONTEXT_PATH_SEPARATOR
				.concat(ApplicationConstant.CONTEXT_PATH_AUTH_RESOURCES)
				.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR).concat(ApplicationConstant.CONTEXT_PATH_ALL));
		web.ignoring().antMatchers(ApplicationConstant.CONTEXT_PATH_SEPARATOR
				.concat(ApplicationConstant.CONTEXT_PATH_AUTH_ACTUATOR)
				.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR).concat(ApplicationConstant.CONTEXT_PATH_ALL));
	}

	@Bean
	public OpenIdConnectFilter myFilter() {
		final OpenIdConnectFilter filter = new OpenIdConnectFilter(
				ApplicationConstant.CONTEXT_PATH_SEPARATOR + ApplicationConstant.CONTEXT_PATH_AUTH_REPLY);
		filter.setRestTemplate(restTemplate);
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new LogRequestFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
				.addFilterAfter(myFilter(), OAuth2ClientContextFilter.class).httpBasic()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(
						ApplicationConstant.CONTEXT_PATH_SEPARATOR + ApplicationConstant.CONTEXT_PATH_AUTH_REPLY))
				.and().authorizeRequests().anyRequest().authenticated();
	}

}