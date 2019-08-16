package com.kingfisher.agile.auth.client.builder;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kingfisher.agile.auth.client.model.MetaData;

import static com.kingfisher.agile.auth.client.builder.DataBuilders.buildMetaData;

public class DataBuildersTest {

	@Test
	public void testBuildMeta() throws JsonParseException, JsonMappingException, IOException {
		assertEquals(new MetaData("a", "b"),
				buildMetaData("ewogICJqU2Vzc2lvbklEIjogImEiLAogICJyZXBseVVSTCI6ICJiIgp9"));
	}

}
