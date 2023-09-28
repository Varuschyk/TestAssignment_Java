package com.alexvar.testAssignmentJava.exception.user;

import com.alexvar.testAssignmentJava.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception of {@link BadRequestException} type
 * that corresponds to {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
 * when user in database already exists.
 *
 * @since 1.0
 * @author AlexVar
 */
public class UserAlreadyExistsException extends BadRequestException {

  @Serial
  private static final long serialVersionUID = -2716554730769234264L;

  public UserAlreadyExistsException(@Nonnull final String message) {
    super(message);
  }
}
