package com.SpringBootAD.SpringBootADConnection.security;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OidcConfigProperty {

	private String clientID;

	private String clientSecret;

	private String redirectURL;

	private String accessTokenURL;

	private String userAuthorizationURL;

	private List<String> scopes;
	
}