package com.kingfisher.agile.auth.client.builder;

import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildRequestData;
import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildResponseData;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.kingfisher.agile.auth.client.model.RequestData;;

public class DataBuildersTest {

	@Test
	public void testRequestData() throws JsonParseException, JsonMappingException, IOException {
		assertEquals(new RequestData("a", "b"),
				buildRequestData("ewogICJqU2Vzc2lvbklEIjogImEiLAogICJyZXBseVVSTCI6ICJiIgp9"));
	}

	@Test
	public void testBuildResponseData() throws JsonGenerationException, JsonMappingException, IOException {
		assertEquals(
				"eyJ1c2VybmFtZSI6IjgwNThmN2U4LWJjNmUtNDI5ZS04YTgxLThlOWQ1MTI0Yzk1MSIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwianNlc3Npb25JRCI6ImEifQ==",
				buildResponseData("a", "8058f7e8-bc6e-429e-8a81-8e9d5124c951",
						Arrays.asList(new String[] { "ROLE_ADMIN" })));
	}

}
