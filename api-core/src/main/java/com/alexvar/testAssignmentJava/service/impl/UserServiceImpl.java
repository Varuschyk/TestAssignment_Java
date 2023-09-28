package com.alexvar.testAssignmentJava.service.impl;

import com.alexvar.testAssignmentJava.api.mapper.UserMapper;
import com.alexvar.testAssignmentJava.common.validator.UserValidator;
import com.alexvar.testAssignmentJava.exception.user.NotSufficientUserAgeException;
import com.alexvar.testAssignmentJava.exception.user.UserAlreadyExistsException;
import com.alexvar.testAssignmentJava.exception.user.UserNotFoundException;
import com.alexvar.testAssignmentJava.model.dto.UserDto;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import com.alexvar.testAssignmentJava.persistence.repository.UserRepository;
import com.alexvar.testAssignmentJava.service.UserService;
import jakarta.annotation.Nonnull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserService} with CRUD operations for {@link User} entity.
 *
 * @see UserRepository
 * @see UserMapper
 * @see UserValidator
 * @since 1.0
 * @author AlexVar
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final UserValidator userValidator;

  @Override
  @Nonnull
  public User saveUser(@Nonnull final UserDto userDto) {
    if (!userValidator.canUseServiceByGivenAge(userDto.getBirthDate()))
      throw new NotSufficientUserAgeException("Not sufficient age to using service!");
    if (userRepository.existsByEmail(userDto.getEmail()))
      throw new UserAlreadyExistsException("User by current email already exists!");

    final var user = userMapper.toUser(userDto);
    final var savedUser = userRepository.save(user);
    log.info("User {} was created!", user.getFirstName());
    return savedUser;
  }

  @Override
  @Nonnull
  public User findUserByEmail(@Nonnull final String email) {
    final var user = userRepository.findByEmail(email);
    if (user == null) throw new UserNotFoundException(String.format("User by email %s not found!", email));
    return user;
  }

  @Nonnull
  @Override
  public List<User> findUsersByBirthDateBetween(@Nonnull final String from, @Nonnull final String to) {
    final var users = userRepository.findUsersByBirthDateBetween(from, to);
    if (users == null) throw new UserNotFoundException("Users on specified birth date interval not found");
    return users;
  }

  @Override
  @Nonnull
  public User updateUser(@Nonnull final UserDto userDto) {
    final var userEmail = userDto.getEmail();
    final var user = userRepository.findByEmail(userEmail);
    if (user == null) throw new UserNotFoundException(String.format("User by email %s not found!", userEmail));
    userMapper.updateUser(user, userDto);
    final var updatedUser = userRepository.save(user);
    log.info("User {} was updated!", updatedUser.getFirstName());
    return updatedUser;
  }

  @Override
  @Nonnull
  public User deleteUserByEmail(@Nonnull final String email) {
    if (!userRepository.existsByEmail(email))
      throw new UserNotFoundException(String.format("User by email %s not found!", email));
    final var deletedUser = userRepository.deleteByEmail(email);
    log.info("User {} was deleted!", deletedUser.getFirstName());
    return deletedUser;
  }

}
