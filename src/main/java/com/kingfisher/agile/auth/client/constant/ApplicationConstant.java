package com.kingfisher.agile.auth.client.constant;

public class ApplicationConstant {

	// prevent initialisation of this call
	private ApplicationConstant() {

	}

	public static final boolean TRUE = Boolean.TRUE;
	public static final boolean FALSE = Boolean.FALSE;

	public static final String BLANK = "";

	public static final String ID_TOKEN = "id_token";
	public static final String AUTHORIZATION_URL = "https://login.microsoftonline.com/%s/oauth2/v2.0/authorize";
	public static final String ACCESS_TOKEN_URL = "https://login.microsoftonline.com/%s/oauth2/v2.0/token";

	public static final String CONTEXT_PATH_SEPARATOR = "/";
	public static final String CONTEXT_PATH_VAR_BRACKET_OPEN = "{";
	public static final String CONTEXT_PATH_VAR_BRACKET_CLOSE = "}";
	public static final String CONTEXT_PATH_MULTI_VAR = "**";
	public static final String CONTEXT_PATH_SINGLE_VAR = "*";

	public static final String CONTEXT_PATH_AUTH_REPLY = "mylogin";
	public static final String CONTEXT_PATH_LOGIN_REQUEST = "login";
	public static final String CONTEXT_PATH_VAR_META = "meta";
	public static final String CONTEXT_PATH_AUTH_RESOURCES = "resources";
	public static final String CONTEXT_PATH_AUTH_ACTUATOR = "actuator";

}
