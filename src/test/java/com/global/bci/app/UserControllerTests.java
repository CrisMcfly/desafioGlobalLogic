package com.global.bci.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.global.bci.app.controllers.UserRestController;
import com.global.bci.app.entity.Phone;
import com.global.bci.app.entity.User;
import com.global.bci.app.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserRestController.class)
class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	@Test
	public void getAllUsersTest() throws Exception{
		User mockUser = new User();
		mockUser.setIdUser(1l);
		mockUser.setEmail("cdonoso@cdonoso.cl");
		mockUser.setName("cdonoso");
		mockUser.setIsActive(true);
		mockUser.setIdUser(1L);
		mockUser.setPassword("1234");
		mockUser.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmlzdGlhbiIsImV4cCI6MTU4MDM2MzI1OSwiaWF0IjoxNTgwMzQ1MjU5fQ.UQm5q5RX73PT1hOiOF3kTOYNoKVcNaxlAXFf2pQT3wY8CgI1Gp3hCcK0jyWm8NwF8vasxzBv74fuCVjZ6INx5A");
		mockUser.setCreateAt(null);
		mockUser.setLastLoginAt(null);
		Phone mockPhone = new Phone();
		mockPhone.setIdPhone(1l);
		mockPhone.setNumber("4654654");
		mockPhone.setCityCode("123");
		mockPhone.setCountryCode("432");
		mockPhone.setUser(mockUser);
		List<Phone> mockPhones = new ArrayList<Phone>();
		mockPhones.add(mockPhone);
		mockUser.setPhones(mockPhones);
		List<User> mockitoUsers = new ArrayList<User>();
		mockitoUsers.add(mockUser);
		Mockito.when(
				userService.findAll()
		).thenReturn(mockitoUsers);
		RequestBuilder rb = MockMvcRequestBuilders.get("/api/users/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(rb).andReturn();
		String expected = "[{\"idUser\":1,\"createAt\":null,\"updateAt\":null,\"lastLoginAt\":null,\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmlzdGlhbiIsImV4cCI6MTU4MDM2MzI1OSwiaWF0IjoxNTgwMzQ1MjU5fQ.UQm5q5RX73PT1hOiOF3kTOYNoKVcNaxlAXFf2pQT3wY8CgI1Gp3hCcK0jyWm8NwF8vasxzBv74fuCVjZ6INx5A\",\"name\":\"cdonoso\",\"email\":\"cdonoso@cdonoso.cl\",\"password\":\"1234\",\"isActive\":true,\"phones\":[{\"idPhone\":1,\"number\":\"4654654\",\"cityCode\":\"123\",\"countryCode\":\"432\"}]}]";
		System.out.println(result.getResponse());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createUserTest() throws Exception{
		User mockUser = new User();
		mockUser.setIdUser(1l);
		mockUser.setEmail("cdonoso@cdonoso.cl");
		mockUser.setName("cdonoso");
		mockUser.setIsActive(true);
		mockUser.setIdUser(1L);
		mockUser.setPassword("1234");
		mockUser.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmlzdGlhbiIsImV4cCI6MTU4MDM2MzI1OSwiaWF0IjoxNTgwMzQ1MjU5fQ.UQm5q5RX73PT1hOiOF3kTOYNoKVcNaxlAXFf2pQT3wY8CgI1Gp3hCcK0jyWm8NwF8vasxzBv74fuCVjZ6INx5A");
		mockUser.setCreateAt(null);
		mockUser.setLastLoginAt(null);
		Phone mockPhone = new Phone();
		mockPhone.setIdPhone(1l);
		mockPhone.setNumber("4654654");
		mockPhone.setCityCode("123");
		mockPhone.setCountryCode("432");
		mockPhone.setUser(mockUser);
		List<Phone> mockPhones = new ArrayList<Phone>();
		mockPhones.add(mockPhone);
		mockUser.setPhones(mockPhones);
		List<User> mockitoUsers = new ArrayList<User>();
		mockitoUsers.add(mockUser);
		Mockito.when(
				userService.save(mockUser)
		).thenReturn(mockUser);
		String jsonExample = "{\"name\":\"cristian\",\"email\":\"cds@cd.s.cl\",\"password\": \"Ass12\",\"phones\":[{\"number\":\"1234560\",\"cityCode\": \"123\",\"countryCode\": \"12\"}]}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/users/")
				.accept(MediaType.APPLICATION_JSON).content(jsonExample)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}
