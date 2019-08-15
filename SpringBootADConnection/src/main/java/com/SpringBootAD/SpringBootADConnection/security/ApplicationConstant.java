package com.SpringBootAD.SpringBootADConnection.security;

public class ApplicationConstant {

	private ApplicationConstant() {

	}

	public static final String AUTHORIZATION_URL = "https://login.microsoftonline.com/%s/oauth2/v2.0/authorize";
	public static final String ACCESS_TOKEN_URL = "https://login.microsoftonline.com/%s/oauth2/v2.0/token";

	public static final String SYMBOL_COMMA = ",";

	public static final boolean TRUE = Boolean.TRUE;
	public static final boolean FALSE = Boolean.FALSE;

}
