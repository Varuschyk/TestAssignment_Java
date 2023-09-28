package com.alexvar.testAssignmentJava.api.rest;

import com.alexvar.testAssignmentJava.api.mapper.UserMapper;
import com.alexvar.testAssignmentJava.model.dto.UserDto;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import com.alexvar.testAssignmentJava.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with CRUD operations relates to {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity.
 *
 * @see UserService
 * @see UserMapper
 * @since 1.0
 * @author AlexVar
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  /**
   * Provides all available {@link com.alexvar.testAssignmentJava.persistence.entity.User} entities
   * by {@link User#getBirthDate()} between specified interval.
   * <p>
   *   For example:
   *   {@code /api/user?from=1-11-1777&to=1-11-2004}
   * </p>
   *
   * @param from from specified {@link User#getBirthDate()}.
   * @param to to specified {@link User#getBirthDate()}.
   * @return {@link UserDto} representation as JSON response.
   */
  @GetMapping(params = {"from", "to"})
  public ResponseEntity<List<UserDto>> findUserByDateBirthBetween(@RequestParam(name = "from")
                                                 @NotBlank @Valid final String from,
                                                       @RequestParam(name = "to")
                                                 @NotBlank @Valid final String to) {
    final var users = userService.findUsersByBirthDateBetween(from, to);
    final var userResponse = userMapper.toUserDtoList(users);
    return ResponseEntity.ok(userResponse);
  }

  /**
   * Provides {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
   * by specified {@link User#getEmail()}.
   *
   * @param email parameter by which entity will be found.
   * @return {@link UserDto} representation as JSON response.
   */
  @GetMapping(params = "email")
  public ResponseEntity<UserDto> findUserByEmail(@RequestParam(name = "email")
                                                   @NotBlank @Valid final String email) {
    final var user = userService.findUserByEmail(email);
    final var userResponse = userMapper.toUserDto(user);
    return ResponseEntity.ok(userResponse);
  }

  /**
   * Saves {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
   * in database by providing {@link UserDto} in request body.
   * <p>
   *   Entity creation allowed only if client is older than 18 y.o.
   * </p>
   *
   * @param userDto {@link UserDto} representation in request body as input data.
   * @return saved entity as {@link UserDto} representation as JSON response.
   */
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @NotNull @Valid final UserDto userDto) {
    final var user = userService.saveUser(userDto);
    final var userResponse = userMapper.toUserDto(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }

  /**
   * Updates {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
   * in database by providing renewed data {@link UserDto} in request body.
   *
   * @param userDto {@link UserDto} representation in request body as input data.
   * @return saved entity as {@link UserDto} representation as JSON response.
   */
  @PutMapping
  public ResponseEntity<UserDto> updateUser(@RequestBody @NotNull @Valid final UserDto userDto) {
    final var user = userService.updateUser(userDto);
    final var userResponse = userMapper.toUserDto(user);
    return ResponseEntity.ok(userResponse);
  }

  /**
   * Deletes {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
   * from database by provided {@link User#getEmail()}.
   *
   * @param email parameter by which entity will be found and deleted.
   * @return deleted entity as {@link UserDto} representation as JSON response.
   */
  @DeleteMapping
  public ResponseEntity<UserDto> deleteUserByEmail(@RequestParam(name = "email")
                                                     @NotBlank @Valid final String email) {
    final var deletedUser = userService.deleteUserByEmail(email);
    final var userResponse = userMapper.toUserDto(deletedUser);
    return ResponseEntity.ok(userResponse);
  }
}
