package com.kingfisher.agile.auth.client.service;

import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildMetaData;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kingfisher.agile.auth.client.model.MetaData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OidcAuthenticationService {

	@Autowired
	AccessLogService accessLogService;

	public ResponseEntity<String> generateSuccessResponse(String httpRequestMeta)
			throws JsonParseException, JsonMappingException, IOException {

		MetaData metaData = buildMetaData(httpRequestMeta);
		accessLogService.logSucceeded(metaData);

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		final String username = authentication.getName();

		authentication.getAuthorities().stream().forEach(x -> System.out.println(x));
		String roles = authentication.getAuthorities().stream().map(x -> x.getAuthority()).reduce("",
				(x, y) -> x == "" ? y : x + "," + y);

		log.debug("Security content created for user : {} with roles", username, roles);

		HttpHeaders headers = new HttpHeaders();
		headers.add("location", metaData.getReplyURL() + "?username=" + username + "&roles=" + roles + "&jSessionID"
				+ metaData.getJSessionID());
		ResponseEntity<String> response = new ResponseEntity<>(httpRequestMeta, HttpStatus.OK);
		return response;
	}

}
