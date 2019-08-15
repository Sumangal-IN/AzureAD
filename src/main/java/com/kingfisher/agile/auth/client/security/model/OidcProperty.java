package com.kingfisher.agile.auth.client.security.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OidcProperty {

	private String clientID;

	private String clientSecret;

	private String redirectURL;

	private String accessTokenURL;

	private String userAuthorizationURL;

	private List<String> scopes;
	
}