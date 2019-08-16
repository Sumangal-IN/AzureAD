package com.kingfisher.agile.auth.client.service;

import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildRequestData;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kingfisher.agile.auth.client.builder.SuccessResponseBuilder;
import com.kingfisher.agile.auth.client.model.RequestData;

@Service
public class OidcAuthenticationService {

	@Autowired
	AccessLogService accessLogService;

	public ResponseEntity<String> generateSuccessResponse(final String httpRequestMeta)
			throws JsonParseException, JsonMappingException, IOException {
		final RequestData requestData = buildRequestData(httpRequestMeta);
		accessLogService.logSucceeded(requestData);
		return SuccessResponseBuilder.build(requestData);
	}

}
