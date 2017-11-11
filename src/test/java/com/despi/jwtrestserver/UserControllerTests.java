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

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<User> userList = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserFactory userFactory;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertThat(this.mappingJackson2HttpMessageConverter).isNotNull();
	}

	@Before
	public void setup() throws Exception {
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

	//TODO: test update existing
	//TODO: test duplicate name (exception thrown)
	//TODO: test empty name (exception thrown)
}
