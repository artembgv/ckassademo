package ru.artembgv.ckassademo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ru.artembgv.ckassademo.dao.PersonRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenPostRequestToPersonAndValidPerson_thenCorrectResponse() throws Exception {
		String person = "{\"fio\": \"Иван\", \"inn\" : 123456789012}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/createPerson").content(person).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void whenPostRequestToPersonAndInValidPerson_thenCorrectResponse() throws Exception {
		String person = "{\"fio\": \"\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/createPerson").content(person)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
