package com.alexvar.testAssignmentJava.api.controller.rest;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexvar.testAssignmentJava.api.mapper.UserMapper;
import com.alexvar.testAssignmentJava.contants.controller.rest.TestUserControllerConstants;
import com.alexvar.testAssignmentJava.contants.url.TestUrlConstants;
import com.alexvar.testAssignmentJava.contants.url.param.TestUrlParameterConstants;
import com.alexvar.testAssignmentJava.exception.user.NotSufficientUserAgeException;
import com.alexvar.testAssignmentJava.exception.user.UserAlreadyExistsException;
import com.alexvar.testAssignmentJava.exception.user.UserNotFoundException;
import com.alexvar.testAssignmentJava.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userService;

  @MockBean
  private UserMapper userMapper;

  @Test
  @Description("GET method: Should return user by email and return 200 OK status")
  void shouldReturnUserByEmailSuccessfully() throws Exception {
    when(userService.findUserByEmail(TestUserControllerConstants.USER_EMAIL))
        .thenReturn(TestUserControllerConstants.USER_ENTITY);
    when(userMapper.toUserDto(TestUserControllerConstants.USER_ENTITY))
        .thenReturn(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(get(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.USER_EMAIL_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_EMAIL)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(TestUserControllerConstants.USER_EMAIL))
        .andExpect(jsonPath("$.firstName").value(TestUserControllerConstants.USER_FIRST_NAME))
        .andExpect(jsonPath("$.lastName").value(TestUserControllerConstants.USER_LAST_NAME))
        .andExpect(jsonPath("$.birthDate").value(TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .andExpect(jsonPath("$.address").value(TestUserControllerConstants.USER_ADDRESS))
        .andExpect(jsonPath("$.phoneNumber").value(TestUserControllerConstants.USER_PHONE_NUMBER));

    verify(userService, only()).findUserByEmail(TestUserControllerConstants.USER_EMAIL);
    verify(userMapper, only()).toUserDto(TestUserControllerConstants.USER_ENTITY);
  }

  @Test
  @Description("GET method: Should return users by date birth between and return 200 OK status")
  void shouldReturnUsersByDateBirthBetweenSuccessfully() throws Exception {
    when(userService.findUsersByBirthDateBetween(TestUserControllerConstants.USER_BIRTH_DAY_2000,
        TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .thenReturn(TestUserControllerConstants.USER_ENTITY_LIST);
    when(userMapper.toUserDtoList(TestUserControllerConstants.USER_ENTITY_LIST))
        .thenReturn(TestUserControllerConstants.USER_DTO_LIST);

    mockMvc.perform(get(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(
                TestUrlParameterConstants.USER_BIRTH_DAY_FROM_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_BIRTH_DAY_2000)
            .param(
                TestUrlParameterConstants.USER_BIRTH_DAY_TO_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_BIRTH_DAY_2004)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value(TestUserControllerConstants.USER_EMAIL))
        .andExpect(jsonPath("$[0].firstName").value(TestUserControllerConstants.USER_FIRST_NAME))
        .andExpect(jsonPath("$[0].lastName").value(TestUserControllerConstants.USER_LAST_NAME))
        .andExpect(jsonPath("$[0].birthDate").value(TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .andExpect(jsonPath("$[0].address").value(TestUserControllerConstants.USER_ADDRESS))
        .andExpect(jsonPath("$[0].phoneNumber").value(TestUserControllerConstants.USER_PHONE_NUMBER));

    verify(userService, only()).findUsersByBirthDateBetween(
        TestUserControllerConstants.USER_BIRTH_DAY_2000, TestUserControllerConstants.USER_BIRTH_DAY_2004);
    verify(userMapper, only()).toUserDtoList(TestUserControllerConstants.USER_ENTITY_LIST);
  }

  @Test
  @Description("GET method: Should throw NotFoundException when user by email not found and return 404 NOT_FOUND status")
  void shouldThrowUserNotFoundExceptionWhenFindUserByEmail() throws Exception {
    when(userService.findUserByEmail(TestUserControllerConstants.USER_EMAIL))
        .thenThrow(UserNotFoundException.class);

    mockMvc.perform(get(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.USER_EMAIL_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_EMAIL)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userService, only()).findUserByEmail(TestUserControllerConstants.USER_EMAIL);
  }

  @Test
  @Description("GET method: Should throw NotFoundException when users by "
      + "birth day between not found and return 404 NOT_FOUND status")
  void shouldThrowUserNotFoundExceptionWhenFindUsersByBirthDayBetween() throws Exception {
    when(userService.findUsersByBirthDateBetween(TestUserControllerConstants.USER_BIRTH_DAY_2000,
        TestUserControllerConstants.USER_BIRTH_DAY_2004)).thenThrow(UserNotFoundException.class);

    mockMvc.perform(get(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(
                TestUrlParameterConstants.USER_BIRTH_DAY_FROM_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_BIRTH_DAY_2000)
            .param(
                TestUrlParameterConstants.USER_BIRTH_DAY_TO_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_BIRTH_DAY_2004)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userService, only()).findUsersByBirthDateBetween(
        TestUserControllerConstants.USER_BIRTH_DAY_2000, TestUserControllerConstants.USER_BIRTH_DAY_2004);
  }

  @Test
  @Description("POST method: Should create user successfully and return 201 CREATED status")
  void shouldCreateUserSuccessfully() throws Exception {
    when(userService.saveUser(TestUserControllerConstants.USER_DTO))
        .thenReturn(TestUserControllerConstants.USER_ENTITY);
    when(userMapper.toUserDto(TestUserControllerConstants.USER_ENTITY))
        .thenReturn(TestUserControllerConstants.USER_DTO);

    final var requestBody = objectMapper.writeValueAsString(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(post(TestUrlConstants.USER_CONTROLLER_PATH)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value(TestUserControllerConstants.USER_EMAIL))
        .andExpect(jsonPath("$.firstName").value(TestUserControllerConstants.USER_FIRST_NAME))
        .andExpect(jsonPath("$.lastName").value(TestUserControllerConstants.USER_LAST_NAME))
        .andExpect(jsonPath("$.birthDate").value(TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .andExpect(jsonPath("$.address").value(TestUserControllerConstants.USER_ADDRESS))
        .andExpect(jsonPath("$.phoneNumber").value(TestUserControllerConstants.USER_PHONE_NUMBER));

    verify(userService, only()).saveUser(TestUserControllerConstants.USER_DTO);
    verify(userMapper, only()).toUserDto(TestUserControllerConstants.USER_ENTITY);
  }

  @Test
  @Description("POST method: Should throw AlreadyExists exception when create user and return 400 BAD_REQUEST status")
  void shouldThrowUserAlreadyExistsExceptionWhenCreateUser() throws Exception {
    when(userService.saveUser(TestUserControllerConstants.USER_DTO))
        .thenThrow(UserAlreadyExistsException.class);

    final var requestBody = objectMapper.writeValueAsString(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(post(TestUrlConstants.USER_CONTROLLER_PATH)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    verify(userService, only()).saveUser(TestUserControllerConstants.USER_DTO);
  }

  @Test
  @Description("POST method: Should throw NotSufficientUserAgeException "
      + "exception when create user and return 400 BAD_REQUEST status")
  void shouldThrowNotSufficientUserAgeExceptionWhenCreateUser() throws Exception {
    when(userService.saveUser(TestUserControllerConstants.USER_DTO))
        .thenThrow(NotSufficientUserAgeException.class);

    final var requestBody = objectMapper.writeValueAsString(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(post(TestUrlConstants.USER_CONTROLLER_PATH)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    verify(userService, only()).saveUser(TestUserControllerConstants.USER_DTO);
  }

  @Test
  @Description("PUT method: Should update user successfully and return 200 OK status")
  void shouldUpdateUserSuccessfully() throws Exception {
    when(userService.updateUser(TestUserControllerConstants.USER_DTO))
        .thenReturn(TestUserControllerConstants.USER_ENTITY);
    when(userMapper.toUserDto(TestUserControllerConstants.USER_ENTITY))
        .thenReturn(TestUserControllerConstants.USER_DTO);

    final var requestBody = objectMapper.writeValueAsString(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(put(TestUrlConstants.USER_CONTROLLER_PATH)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(TestUserControllerConstants.USER_EMAIL))
        .andExpect(jsonPath("$.firstName").value(TestUserControllerConstants.USER_FIRST_NAME))
        .andExpect(jsonPath("$.lastName").value(TestUserControllerConstants.USER_LAST_NAME))
        .andExpect(jsonPath("$.birthDate").value(TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .andExpect(jsonPath("$.address").value(TestUserControllerConstants.USER_ADDRESS))
        .andExpect(jsonPath("$.phoneNumber").value(TestUserControllerConstants.USER_PHONE_NUMBER));

    verify(userService, only()).updateUser(TestUserControllerConstants.USER_DTO);
    verify(userMapper, only()).toUserDto(TestUserControllerConstants.USER_ENTITY);
  }

  @Test
  @Description("PUT method: Should throw NotFoundException when update user and return 404 NOT_FOUND status")
  void shouldThrowUserNotFoundExceptionWhenUpdateUser() throws Exception {
    when(userService.updateUser(TestUserControllerConstants.USER_DTO))
        .thenThrow(UserNotFoundException.class);

    final var requestBody = objectMapper.writeValueAsString(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(put(TestUrlConstants.USER_CONTROLLER_PATH)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userService, only()).updateUser(TestUserControllerConstants.USER_DTO);
  }

  @Test
  @Description("DELETE method: Should delete user successfully and return 200 OK status")
  void shouldDeleteUserSuccessfully() throws Exception {
    when(userService.deleteUserByEmail(TestUserControllerConstants.USER_EMAIL))
        .thenReturn(TestUserControllerConstants.USER_ENTITY);
    when(userMapper.toUserDto(TestUserControllerConstants.USER_ENTITY))
        .thenReturn(TestUserControllerConstants.USER_DTO);

    mockMvc.perform(delete(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.USER_EMAIL_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_EMAIL)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(TestUserControllerConstants.USER_EMAIL))
        .andExpect(jsonPath("$.firstName").value(TestUserControllerConstants.USER_FIRST_NAME))
        .andExpect(jsonPath("$.lastName").value(TestUserControllerConstants.USER_LAST_NAME))
        .andExpect(jsonPath("$.birthDate").value(TestUserControllerConstants.USER_BIRTH_DAY_2004))
        .andExpect(jsonPath("$.address").value(TestUserControllerConstants.USER_ADDRESS))
        .andExpect(jsonPath("$.phoneNumber").value(TestUserControllerConstants.USER_PHONE_NUMBER));

    verify(userService, only()).deleteUserByEmail(TestUserControllerConstants.USER_EMAIL);
    verify(userMapper, only()).toUserDto(TestUserControllerConstants.USER_ENTITY);
  }

  @Test
  @Description("DELETE method: Should throw NotFoundException when delete user and return 404 NOT_FOUND status")
  void shouldThrowUserNotFoundExceptionWhenDeleteUser() throws Exception {
    when(userService.deleteUserByEmail(TestUserControllerConstants.USER_EMAIL))
        .thenThrow(UserNotFoundException.class);

    mockMvc.perform(delete(TestUrlConstants.USER_CONTROLLER_PATH)
            .param(TestUrlParameterConstants.USER_EMAIL_PARAMETER_CONTROLLER_GET,
                TestUserControllerConstants.USER_EMAIL)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(userService, only()).deleteUserByEmail(TestUserControllerConstants.USER_EMAIL);
  }

}
