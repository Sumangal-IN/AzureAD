package com.kingfisher.agile.auth.client.builder;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.model.AccessLog;
import com.kingfisher.agile.auth.client.model.RequestData;
import com.kingfisher.agile.auth.client.model.ResponseData;

public class DataBuilders {

	public static AccessLog buildAccessLog(final RequestData requestData) {
		return AccessLog.builder().jSessionID(requestData.getJSessionID()).replyURL(requestData.getReplyURL())
				.requestedOn(new Date()).build();
	}

	public static RequestData buildRequestData(final String meta)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(Base64.getDecoder().decode(meta), RequestData.class);
	}

	public static String extractMeta(final String requestURI) {
		return requestURI.replace(
				ApplicationConstant.CONTEXT_PATH_SEPARATOR.concat(ApplicationConstant.CONTEXT_PATH_LOGIN_REQUEST)
						.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR),
				ApplicationConstant.BLANK);
	}

	public static String buildResponseData(final String jSessionID, final String username, final List<String> roles)
			throws JsonGenerationException, JsonMappingException, IOException {
		ResponseData data = ResponseData.builder().jSessionID(jSessionID).username(username).roles(roles).build();
		return new String(Base64.getEncoder().encode(new ObjectMapper().writeValueAsBytes(data)));
	}

}
