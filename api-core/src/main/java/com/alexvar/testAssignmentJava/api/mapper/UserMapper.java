package com.alexvar.testAssignmentJava.api.mapper;

import com.alexvar.testAssignmentJava.model.dto.UserDto;
import com.alexvar.testAssignmentJava.persistence.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper for {@link User} entity.
 * <p>
 *   Allows fast with auto implementation create methods for mapping.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  /**
   * Maps a {@link User} object to a {@link UserDto} object.
   *
   * @param user The {@link User} object to be mapped.
   * @return The resulting {@link UserDto} object.
   */
  UserDto toUserDto(User user);

  /**
   * Maps a {@link UserDto} object to a {@link User} object.
   *
   * @param userDto The {@link UserDto} object to be mapped.
   * @return The resulting {@link User} object.
   */
  User toUser(UserDto userDto);

  /**
   * Converts list of {@link User} into list of {@link UserDto}.
   *
   * @param users list of {@link User} that will be converted.
   * @return the resulting list of {@link UserDto} objects
   */
  List<UserDto> toUserDtoList(List<User> users);

  /**
   * This method updates the User object with the data from the UserDto.
   *
   * @param user    The User object to be updated.
   * @param userDto The source of the updated data.
   */
  void updateUser(@MappingTarget User user, UserDto userDto);
}
