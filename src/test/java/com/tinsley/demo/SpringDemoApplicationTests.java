package com.tinsley.demo;

import com.tinsley.demo.services.StringPermutation;
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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class SpringDemoApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	StringPermutation sp;

	private String fibonacciUrl = "http://localhost:8080/fibonacci?num=";
	private String primeUrl = "http://localhost:8080/prime?num=";
	private String wordUrl = "http://localhost:8080/stringpermutations/?word=cat";


	@Test
	void fibonacciRestTest() throws Exception {
		SpringDemoApplicationHelper sdah = new SpringDemoApplicationHelper();

		String res = sdah.getRetValFibonacci();
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(fibonacciUrl + Integer.toString(1))
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
		SpringDemoApplicationHelper sdah = new SpringDemoApplicationHelper();
		for(int x = 0; x<=2; x++) {
			String resTrue = sdah.getResTrue();
			String resFalse = sdah.getResFalse();
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get(primeUrl + Integer.toString(x))
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
			//test response back
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			//check body
			if(x==0 || x==1 ) {
				assertEquals(resFalse, result.getResponse().getContentAsString());
			}
			else if( x == 2) {
				assertEquals(resTrue, result.getResponse().getContentAsString());
			}
		}

	}

	@Test
	void StringPermutationTest() throws Exception {

		SpringDemoApplicationHelper sdah = new SpringDemoApplicationHelper();
		String res = sdah.getResWord();

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(wordUrl)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		//test response back
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		//check body content
		assertEquals(res,result.getResponse().getContentAsString());
	}



}
