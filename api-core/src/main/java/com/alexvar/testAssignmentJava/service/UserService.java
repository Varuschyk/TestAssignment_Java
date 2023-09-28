package com.alexvar.testAssignmentJava.service;

import com.alexvar.testAssignmentJava.model.dto.UserDto;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * Service with CRUD operations according {@link User} entity.
 *
 * @since 1.0
 * @author AlexVar
 */
public interface UserService {

  /**
   * Saves {@link User} entity in database, by specified request body.
   * <p>
   *   Entity creation allowed only if client is older than 18 y.o.
   * </p>
   *
   * @param userDto {@link UserDto} representation in request body as input data.
   * @return saved {@link User}.
   */
  @Nonnull
  User saveUser(@Nonnull UserDto userDto);

  /**
   * Provides {@link User} entity by specified {@link User#getEmail()}.
   *
   * @param email parameter by which entity will be found.
   * @return provided {@link User} entity.
   */
  @Nonnull
  User findUserByEmail(@Nonnull String email);

  /**
   * Provides all available {@link User} entities by {@link User#getBirthDate()} between specified interval.
   *
   * @param from from specified {@link User#getBirthDate()}.
   * @param to to specified {@link User#getBirthDate()}.
   * @return provided {@link List<User>} that was found between specified interval.
   */
  @Nonnull
  List<User> findUsersByBirthDateBetween(@Nonnull String from, @Nonnull String to);

  /**
   * Updates {@link User} entity in database by providing renewed data {@link UserDto} in request body.
   *
   * @param userDto {@link UserDto} representation in request body as input data.
   * @return updated {@link User} entity.
   */
  @Nonnull
  User updateUser(@Nonnull UserDto userDto);

  /**
   * Deltes {@link User} entity from database by providing {@link User#getEmail()}.
   *
   * @param email parameter by which entity will be found.
   * @return deleted {@link User} entity.
   */
  @Nonnull
  User deleteUserByEmail(@Nonnull String email);
}
