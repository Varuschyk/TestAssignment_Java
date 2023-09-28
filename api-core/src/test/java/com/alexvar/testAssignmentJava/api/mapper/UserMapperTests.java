package com.alexvar.testAssignmentJava.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.alexvar.testAssignmentJava.contants.mapper.TestUserMapperConstants;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserMapperTests {

  private final UserMapper userMapper = new UserMapperImpl();

  @Test
  void testToUserDto() {
    final var userDto = userMapper.toUserDto(TestUserMapperConstants.USER_ENTITY);

    assertEquals(TestUserMapperConstants.USER_EMAIL, userDto.getEmail());
    assertEquals(TestUserMapperConstants.USER_FIRST_NAME, userDto.getFirstName());
    assertEquals(TestUserMapperConstants.USER_LAST_NAME, userDto.getLastName());
    assertEquals(TestUserMapperConstants.USER_BIRTH_DAY_2004, userDto.getBirthDate());
    assertEquals(TestUserMapperConstants.USER_ADDRESS, userDto.getAddress());
    assertEquals(TestUserMapperConstants.USER_PHONE_NUMBER, userDto.getPhoneNumber());
  }

  @Test
  void testToUserDto_WithNullUser() {
    final var userDto = userMapper.toUserDto(null);
    assertNull(userDto);
  }

  @Test
  void testToUser() {
    final var user = userMapper.toUser(TestUserMapperConstants.USER_DTO);

    assertEquals(TestUserMapperConstants.USER_EMAIL, user.getEmail());
    assertEquals(TestUserMapperConstants.USER_FIRST_NAME, user.getFirstName());
    assertEquals(TestUserMapperConstants.USER_LAST_NAME, user.getLastName());
    assertEquals(TestUserMapperConstants.USER_BIRTH_DAY_2004, user.getBirthDate());
    assertEquals(TestUserMapperConstants.USER_ADDRESS, user.getAddress());
    assertEquals(TestUserMapperConstants.USER_PHONE_NUMBER, user.getPhoneNumber());
  }

  @Test
  void testToUser_WithNullUserDto() {
    final var user = userMapper.toUser(null);
    assertNull(user);
  }

  @Test
  void testToListUser() {
    final var userList = userMapper.toUserDtoList(TestUserMapperConstants.USER_ENTITY_LIST);

    assertEquals(TestUserMapperConstants.USER_EMAIL, userList.get(0).getEmail());
    assertEquals(TestUserMapperConstants.USER_FIRST_NAME, userList.get(0).getFirstName());
    assertEquals(TestUserMapperConstants.USER_LAST_NAME, userList.get(0).getLastName());
    assertEquals(TestUserMapperConstants.USER_BIRTH_DAY_2004, userList.get(0).getBirthDate());
    assertEquals(TestUserMapperConstants.USER_ADDRESS, userList.get(0).getAddress());
    assertEquals(TestUserMapperConstants.USER_PHONE_NUMBER, userList.get(0).getPhoneNumber());
  }

  @Test
  void testToUserDtoList_WithNullUserList() {
    final var userList = userMapper.toUserDtoList(null);
    assertNull(userList);
  }

  @Test
  void updateUserValidInputUserIsUpdated() {
    final var user = new User();

    userMapper.updateUser(user, TestUserMapperConstants.USER_DTO);

    assertEquals(TestUserMapperConstants.USER_EMAIL, user.getEmail());
    assertEquals(TestUserMapperConstants.USER_FIRST_NAME, user.getFirstName());
    assertEquals(TestUserMapperConstants.USER_LAST_NAME, user.getLastName());
    assertEquals(TestUserMapperConstants.USER_BIRTH_DAY_2004, user.getBirthDate());
    assertEquals(TestUserMapperConstants.USER_ADDRESS, user.getAddress());
    assertEquals(TestUserMapperConstants.USER_PHONE_NUMBER, user.getPhoneNumber());
  }
}