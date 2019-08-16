package com.kingfisher.agile.auth.client.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResponseData {
	String jSessionID;
	String username;
	List<String> roles;
}
