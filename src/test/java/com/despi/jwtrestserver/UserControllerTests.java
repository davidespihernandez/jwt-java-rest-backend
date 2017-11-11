package com.despi.jwtrestserver;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.factory.UserFactory;
import com.despi.jwtrestserver.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtRestServerApplication.class)
@WebAppConfiguration
@Transactional
public class UserControllerTests {

	private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private static final String API = "/api/users/";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	UserFactory userFactory;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void readUser() throws Exception {
		User user = userFactory.create();
		MvcResult mvcResult = mockMvc.perform(get(API + user.getId()))
				.andExpect(status().isOk())
				.andReturn();
		UserDto userDto = mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
		assertThat(userDto.getId()).isEqualTo(user.getId());
		assertThat(userDto.getName()).isEqualTo(user.getName());
		assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
	}

	@Test
	public void createUser() throws Exception {
		UserDto userDto = UserFactory.createDto();
		MvcResult mvcResult = mockMvc.perform(post(API)
				.content(mapper.writeValueAsString(userDto))
				.contentType(contentType))
				.andExpect(status().isOk())
				.andReturn();
		UserDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
		assertThat(returned.getId()).isNotNull();
		assertThat(userDto.getName()).isEqualTo(returned.getName());
		assertThat(userDto.getEmail()).isEqualTo(returned.getEmail());
	}

	@Test
	public void updateUser() throws Exception {
		User user = userFactory.create();
		UserDto userDto = UserFactory.createDto(user);
		String newName = "New " + UUID.randomUUID().toString();
		userDto.setName(newName);
		MvcResult mvcResult = mockMvc.perform(post(API)
				.content(mapper.writeValueAsString(userDto))
				.contentType(contentType))
				.andExpect(status().isOk())
				.andReturn();
		UserDto returned = mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
		assertThat(returned.getId()).isEqualTo(user.getId());
		assertThat(userDto.getName()).isEqualTo(returned.getName());
		assertThat(userDto.getEmail()).isEqualTo(returned.getEmail());
	}

	@Test
	public void nameMustBeNotNullOrEmpty() throws Exception {
		UserDto userDto = UserFactory.createDto();
		userDto.setName("");
		mockMvc.perform(post(API)
				.content(mapper.writeValueAsString(userDto))
				.contentType(contentType))
				.andExpect(status().isNotAcceptable());
	}

	@Test
	public void duplicateEmailFails() throws Exception {
		UserDto userDto = UserFactory.createDto();
		mockMvc.perform(post(API)
				.content(mapper.writeValueAsString(userDto))
				.contentType(contentType))
				.andExpect(status().isOk());
		//a second call will fail as the email is repeated
		mockMvc.perform(post(API)
				.content(mapper.writeValueAsString(userDto))
				.contentType(contentType))
				.andExpect(status().isNotAcceptable());
	}
}
