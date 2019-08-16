package com.kingfisher.agile.auth.client.service;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OidcAuthenticationService {

	public ResponseEntity<String> httpResponse(String httpRequestMeta) {
		String jsessionID = new String(Base64.getDecoder().decode(httpRequestMeta)).split(":")[0];
		String returnURL = new String(
				Base64.getDecoder().decode(new String(Base64.getDecoder().decode(httpRequestMeta)).split(":")[1]));
		log.debug("incoming>> jsessionID: {}", jsessionID);
		log.debug("incoming>> returnURL: {}", returnURL);

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		final String username = authentication.getName();

		authentication.getAuthorities().stream().forEach(x -> System.out.println(x));
		// concatenate each roles in comma separated format
		String roles = authentication.getAuthorities().stream().map(x -> x.getAuthority()).reduce("",
				(x, y) -> x == "" ? y : x + "," + y);

		log.debug("Security content created for user : {} with roles", username, roles);

		HttpHeaders headers = new HttpHeaders();
		headers.add("location", returnURL + "?username=" + username + "&roles=" + roles + "&jSessionID" + jsessionID);
		ResponseEntity<String> response = new ResponseEntity<>(httpRequestMeta, HttpStatus.OK);
		return response;
	}

}
