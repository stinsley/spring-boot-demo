package com.tinsley.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class SpringDemoApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void fibonacciRestTest() throws Exception {
		String res = "[\"0\"]";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:8080/fibonacci?num=1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//test response back
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		//check body
		assertEquals(res,result.getResponse().getContentAsString());

	}

	@Test
	void primeRestTest() throws Exception {
		String res = "true";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:8080/prime?num=3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//test response back
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		//check body
		assertEquals(res,result.getResponse().getContentAsString());
	}





}
