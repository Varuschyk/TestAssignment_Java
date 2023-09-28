package com.alexvar.testAssignmentJava.contants.mapper;

import com.alexvar.testAssignmentJava.model.dto.UserDto;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUserMapperConstants {
  public final String USER_EMAIL = "testuseremail@gmail.com";
  public final String USER_FIRST_NAME = "testFirstName";
  public final String USER_LAST_NAME = "testLastName";
  public final String USER_BIRTH_DAY_2004 = "1-11-2004";
  public final String USER_ADDRESS = "testAddress";
  public final String USER_PHONE_NUMBER = "+380711689012";
  public final User USER_ENTITY = User.builder()
      .id(1L)
      .email(USER_EMAIL)
      .firstName(USER_FIRST_NAME)
      .lastName(USER_LAST_NAME)
      .birthDate(USER_BIRTH_DAY_2004)
      .address(USER_ADDRESS)
      .phoneNumber(USER_PHONE_NUMBER)
      .build();
  public final UserDto USER_DTO = UserDto.builder()
      .email(USER_EMAIL)
      .firstName(USER_FIRST_NAME)
      .lastName(USER_LAST_NAME)
      .birthDate(USER_BIRTH_DAY_2004)
      .address(USER_ADDRESS)
      .phoneNumber(USER_PHONE_NUMBER)
      .build();
  public final List<User> USER_ENTITY_LIST = List.of(USER_ENTITY);
}
