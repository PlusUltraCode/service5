package com.example.restapi;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;




	@Test
	void contextLoads() throws JsonProcessingException {

		var user = new UserRequest();
		user.setName("1");
		user.setEmail("1");
		user.setPhoneNumber("1");

		//객체를 Json 형태로 직렬화 해보기

		var json = objectMapper.writeValueAsString(user);
		System.out.println(json);

		var newUser = objectMapper.readValue(json,UserRequest.class);
		System.out.println(newUser);

	}

}
