package com.kingfisher.agile.auth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.service.OidcAuthenticationService;

@RestController
public class OidcAuthController {

	@Autowired
	OidcAuthenticationService oidcAuthenticationService;

	@GetMapping(ApplicationConstant.CONTEXT_PATH_SEPARATOR + ApplicationConstant.CONTEXT_PATH_LOGIN_REQUEST
			+ ApplicationConstant.CONTEXT_PATH_SEPARATOR + ApplicationConstant.CONTEXT_PATH_VAR_BRACKET_OPEN
			+ ApplicationConstant.CONTEXT_PATH_VAR_META + ApplicationConstant.CONTEXT_PATH_VAR_BRACKET_CLOSE)
	public final ResponseEntity<String> authenticate(
			@PathVariable(ApplicationConstant.CONTEXT_PATH_VAR_META) String httpRequestMeta) {
		return oidcAuthenticationService.httpResponse(httpRequestMeta);
	}

}
