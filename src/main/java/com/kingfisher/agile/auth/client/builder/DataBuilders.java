package com.kingfisher.agile.auth.client.builder;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.kingfisher.agile.auth.client.constant.ApplicationConstant;
import com.kingfisher.agile.auth.client.model.AccessLog;
import com.kingfisher.agile.auth.client.model.MetaData;

public class DataBuilders {

	public static AccessLog buildAccessLog(MetaData metaData) {
		return AccessLog.builder().jSessionID(metaData.getJSessionID()).replyURL(metaData.getReplyURL())
				.requestedOn(new Date()).build();
	}

	public static MetaData buildMetaData(String meta) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(Base64.getDecoder().decode(meta), MetaData.class);
	}

	public static String extractMeta(String requestURI) {
		return requestURI.replace(
				ApplicationConstant.CONTEXT_PATH_SEPARATOR.concat(ApplicationConstant.CONTEXT_PATH_LOGIN_REQUEST)
						.concat(ApplicationConstant.CONTEXT_PATH_SEPARATOR),
				ApplicationConstant.BLANK);
	}

}
