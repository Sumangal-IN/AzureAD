package com.kingfisher.agile.auth.client.builder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildResponseData;

import java.io.IOException;
import java.util.stream.Collectors;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.model.RequestData;

public class SuccessResponseBuilder {

	public static ResponseEntity<String> build(final RequestData requestData)
			throws JsonGenerationException, JsonMappingException, IOException {
		ResponseEntity<String> response = new ResponseEntity<>(prepareRedirectHeader(requestData), HttpStatus.FOUND);
		return response;
	}

	private static HttpHeaders prepareRedirectHeader(final RequestData requestData)
			throws JsonGenerationException, JsonMappingException, IOException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HttpHeaders headers = new HttpHeaders();
		headers.add(ApplicationConstant.HTTP_HEADER_LOCATION,
				requestData.getReplyURL().concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR)
						.concat(buildResponseData(requestData.getJSessionID(), authentication.getName(), authentication
								.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()))));
		return headers;
	}
}
