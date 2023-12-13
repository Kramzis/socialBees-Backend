package com.server.socialBees;

import com.server.socialBees.entity.User;
import com.server.socialBees.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserId_thenReturnUserDTOObject() throws Exception {
        //given
        User user = new User();
        user.setName("Janusz");
        user.setSurname("Kowalski");
        user.setUsername("jskol");
        user.setPassword("password");
        user.setEmail("jkol@gmail.com");
        user.setBirthday(LocalDate.parse("2001-10-10"));
        user.setDeleted(false);

        User savedUser = userRepository.saveAndFlush(user);

        //then
        mvc.perform(get("/user/{userId}", savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Janusz")))
                .andExpect(jsonPath("$.surname", is("Kowalski")))
                .andExpect(jsonPath("$.username", is("jskol")))
                .andExpect(jsonPath("$.email", is("jkol@gmail.com")))
                .andExpect(jsonPath("$.birthday", is("2001-10-10")));

        userRepository.delete(user);
    }
}

