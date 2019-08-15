package com.kingfisher.agile.auth.client.security.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.security.model.CustomAuthentication;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OpenIdConnectFilter extends AbstractAuthenticationProcessingFilter {

	public OAuth2RestOperations restTemplate;

	public OpenIdConnectFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(new NoopAuthenticationManager());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, ServletException {
		OAuth2AccessToken accessToken;
		try {
			accessToken = restTemplate.getAccessToken();
		} catch (final OAuth2Exception e) {
			throw new BadCredentialsException("Could not obtain access token", e);
		}
		try {
			final String idToken = accessToken.getAdditionalInformation().get(ApplicationConstant.ID_TOKEN).toString();
			final Jwt tokenDecoded = JwtHelper.decode(idToken);
			JsonParser parser = new JsonParser();
			JsonElement claimsData = parser.parse(tokenDecoded.getClaims());
			return new CustomAuthentication(claimsData, true);
		} catch (final Exception e) {
			throw new BadCredentialsException("Could not obtain user details from token", e);
		}
	}

	private static class NoopAuthenticationManager implements AuthenticationManager {
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager");
		}

	}
}