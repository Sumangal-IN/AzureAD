package com.SpringBootAD.SpringBootADConnection.controller;

import java.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/login/{meta}")
	public final ResponseEntity<String> authenticate(@PathVariable("meta") String meta) {
		String jsessionID = new String(Base64.getDecoder().decode(meta)).split(":")[0];
		String returnURL = new String(
				Base64.getDecoder().decode(new String(Base64.getDecoder().decode(meta)).split(":")[1]));
		System.out.println("incoming>> jsessionID:" + jsessionID);
		System.out.println("incoming>> returnURL:" + returnURL);

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		final String username = authentication.getName();
		System.out.println(username);

		authentication.getAuthorities().stream().forEach(x -> System.out.println(x));
		// concatenate each roles in comma separated format
		String rolesCSV = authentication.getAuthorities().stream().map(x -> x.getAuthority()).reduce("",
				(x, y) -> x == "" ? y : x + "," + y);

		HttpHeaders headers = new HttpHeaders();
		headers.add("location",
				returnURL + "?username=" + username + "&roles=" + rolesCSV + "&jSessionID" + jsessionID);
		ResponseEntity<String> response = new ResponseEntity<>(headers, HttpStatus.FOUND);
		return response;
	}

}
