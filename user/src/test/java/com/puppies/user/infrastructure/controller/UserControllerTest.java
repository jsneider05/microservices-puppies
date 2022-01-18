package com.puppies.user.infrastructure.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.puppies.user.application.request.CreateUserRequest;
import com.puppies.user.configuration.UserApplicationMock;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = UserApplicationMock.class)
@AutoConfigureMockMvc
class UserControllerTest {

  private static final String URL = "/v1/user";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private ObjectWriter objectWriter;

  private Faker faker;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    faker = new Faker();
  }

  @Test
  void addUserSuccessTest() throws Exception {
    // Arrange
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String password = faker.internet().password();
    String email = faker.internet().emailAddress();
    CreateUserRequest userRequest = CreateUserRequest.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .password(password)
        .dob(LocalDate.now().minusYears(21))
        .build();

    String userRequestJson = objectWriter.writeValueAsString(userRequest);

    // Act
    ResultActions resultActions = mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(userRequestJson));

    UUID idUserCreated = objectMapper.readValue(
        resultActions.andReturn().getResponse().getContentAsString(), UUID.class);

    // Assert
    resultActions.andExpect(status().isCreated());

    assertThat(idUserCreated).isNotNull();
  }

  @Test
  void addUserFailEmailAlreadyExistTest() throws Exception {
    // Arrange
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String password = faker.internet().password();
    String email = "joan@gmail.com";
    CreateUserRequest userRequest = CreateUserRequest.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .password(password)
        .dob(LocalDate.now().minusYears(21))
        .build();

    String userRequestJson = objectWriter.writeValueAsString(userRequest);

    // Act
    ResultActions resultActions = mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(userRequestJson));

    // Assert
    resultActions.andExpect(status().isBadRequest());
  }

}