package com.api.texomovies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TexoMoviesApplicationTests {

	@Test
	void contextLoads() {
	}

}
//TODO: exeplo de testes
//@WebMvcTest
//public class WebLayerTest {

//	@Autowired
//	private MockMvc mockMvc;

//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, World")));
//	}
//}
//TODO: Exemplo 2

//@SpringBootTest
//@AutoConfigureMockMvc
//public class TestingWebApplicationTest {

//	@Autowired
//	private MockMvc mockMvc;

//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, World")));
//	}
//}