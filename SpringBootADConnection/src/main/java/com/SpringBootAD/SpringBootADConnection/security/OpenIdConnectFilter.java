package com.SpringBootAD.SpringBootADConnection.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.google.gson.JsonParser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OpenIdConnectFilter extends AbstractAuthenticationProcessingFilter {

	@AutoConfigureOrder
	public OAuth2RestTemplate restTemplate;

	public OpenIdConnectFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(new NoopAuthenticationManager());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, ServletException {
		try {
			OAuth2AccessToken accessToken = restTemplate.getAccessToken();
			final String idToken = accessToken.getAdditionalInformation().get("id_token").toString();
			final Jwt tokenDecoded = JwtHelper.decode(idToken);
			return new CustomAuthentication(new JsonParser().parse(tokenDecoded.getClaims()));
		} catch (final OAuth2Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Could not obtain access token", e);
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