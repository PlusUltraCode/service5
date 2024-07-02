package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenCreate(){

		var claims = new HashMap<String, Object>();

		claims.put("user_id", 923);

		var expiredAt = LocalDateTime.now().plusMinutes(10);
		var jwtToekn = jwtService.create(claims,expiredAt);
		System.out.println(jwtToekn);
	}

	@Test
	void tokenValidation(){

	}

}
