package com.kingfisher.agile.auth.client.model;

import org.codehaus.jackson.annotate.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {
	
	@JsonProperty("jSessionID")
	String jSessionID;
	
	@JsonProperty("replyURL")
	String replyURL;
	
}
