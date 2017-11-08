package com.despi.jwtrestserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class UserControllerTests {

	@Test
	public void usersController() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> entity = testRestTemplate.getForEntity("http://localhost:8080/api/users", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
