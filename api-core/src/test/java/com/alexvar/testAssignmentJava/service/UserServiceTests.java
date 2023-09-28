package com.alexvar.testAssignmentJava.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alexvar.testAssignmentJava.api.mapper.UserMapper;
import com.alexvar.testAssignmentJava.common.validator.UserValidator;
import com.alexvar.testAssignmentJava.contants.service.user.TestUserServiceConstants;
import com.alexvar.testAssignmentJava.exception.user.NotSufficientUserAgeException;
import com.alexvar.testAssignmentJava.exception.user.UserAlreadyExistsException;
import com.alexvar.testAssignmentJava.exception.user.UserNotFoundException;
import com.alexvar.testAssignmentJava.persistence.repository.UserRepository;
import com.alexvar.testAssignmentJava.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;
  @Mock
  private UserMapper userMapper;
  @Mock
  private UserValidator userValidator;
  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void shouldSaveUserSuccessfully() {
    when(userValidator.canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004))
        .thenReturn(true);
    when(userRepository.existsByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(false);
    when(userMapper.toUser(TestUserServiceConstants.USER_DTO))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);
    when(userRepository.save(TestUserServiceConstants.USER_ENTITY))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);

    userService.saveUser(TestUserServiceConstants.USER_DTO);

    verify(userValidator, only())
        .canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004);
    verify(userRepository, times(1))
        .existsByEmail(TestUserServiceConstants.USER_EMAIL);
    verify(userMapper, only()).toUser(TestUserServiceConstants.USER_DTO);
    verify(userRepository, times(1))
        .save(TestUserServiceConstants.USER_ENTITY);
  }

  @Test
  void shouldThrowNotSufficientUserAgeExceptionWhenSaveUser() {
    when(userValidator.canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004))
        .thenReturn(false);

    assertThrows(NotSufficientUserAgeException.class,
        () -> userService.saveUser(TestUserServiceConstants.USER_DTO));

    verify(userValidator, only())
        .canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004);
  }

  @Test
  void shouldThrowUserAlreadyExistsExceptionWhenSaveUser() {
    when(userValidator.canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004))
        .thenReturn(true);
    when(userRepository.existsByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(true);

    assertThrows(UserAlreadyExistsException.class,
        () -> userService.saveUser(TestUserServiceConstants.USER_DTO));

    verify(userValidator, only())
        .canUseServiceByGivenAge(TestUserServiceConstants.USER_BIRTH_DAY_2004);
    verify(userRepository, times(1))
        .existsByEmail(TestUserServiceConstants.USER_EMAIL);
  }

  @Test
  void shouldFindUserByEmailSuccessfully() {
    when(userRepository.findByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);

    userService.findUserByEmail(TestUserServiceConstants.USER_EMAIL);

    verify(userRepository, only()).findByEmail(TestUserServiceConstants.USER_EMAIL);
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenFindUserByEmail() {
    when(userRepository.findByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(null);

    assertThrows(UserNotFoundException.class,
        () -> userService.findUserByEmail(TestUserServiceConstants.USER_EMAIL));

    verify(userRepository, only()).findByEmail(TestUserServiceConstants.USER_EMAIL);
  }

  @Test
  void shouldFindUserByBirthDayBetweenSuccessfully() {
    when(userRepository.findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004))
        .thenReturn(TestUserServiceConstants.USER_ENTITY_LIST);

    userService.findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004);

    verify(userRepository, only()).findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004);
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenFindUserByBirthDayBetween() {
    when(userRepository.findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004))
        .thenReturn(null);

    assertThrows(UserNotFoundException.class,
        () -> userService.findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004));

    verify(userRepository, only()).findUsersByBirthDateBetween(
        TestUserServiceConstants.USER_BIRTH_DAY_2000, TestUserServiceConstants.USER_BIRTH_DAY_2004);
  }


  @Test
  void shouldUpdateUserSuccessfully() {
    when(userRepository.findByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);
    doNothing().when(userMapper)
        .updateUser(TestUserServiceConstants.USER_ENTITY, TestUserServiceConstants.USER_DTO);
    when(userRepository.save(TestUserServiceConstants.USER_ENTITY))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);

    userService.updateUser(TestUserServiceConstants.USER_DTO);

    verify(userRepository, times(1))
        .findByEmail(TestUserServiceConstants.USER_EMAIL);
    verify(userMapper, only()).updateUser(TestUserServiceConstants.USER_ENTITY,
        TestUserServiceConstants.USER_DTO);
    verify(userRepository, times(1))
        .save(TestUserServiceConstants.USER_ENTITY);
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenUpdateUser() {
    when(userRepository.findByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(null);

    assertThrows(UserNotFoundException.class,
        () -> userService.updateUser(TestUserServiceConstants.USER_DTO));

    verify(userRepository, times(1))
        .findByEmail(TestUserServiceConstants.USER_EMAIL);
  }

  @Test
  void shouldDeleteUserSuccessfully() {
    when(userRepository.existsByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(true);
    when(userRepository.deleteByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(TestUserServiceConstants.USER_ENTITY);

    userService.deleteUserByEmail(TestUserServiceConstants.USER_EMAIL);

    verify(userRepository, times(1))
        .existsByEmail(TestUserServiceConstants.USER_EMAIL);
    verify(userRepository, times(1))
        .deleteByEmail(TestUserServiceConstants.USER_EMAIL);
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenDeleteUser() {
    when(userRepository.existsByEmail(TestUserServiceConstants.USER_EMAIL))
        .thenReturn(false);

    assertThrows(UserNotFoundException.class,
        () -> userService.deleteUserByEmail(TestUserServiceConstants.USER_EMAIL));

    verify(userRepository, times(1))
        .existsByEmail(TestUserServiceConstants.USER_EMAIL);
  }
}
