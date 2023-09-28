package com.alexvar.testAssignmentJava.exception.user;

import com.alexvar.testAssignmentJava.exception.NotFoundException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception of {@link com.alexvar.testAssignmentJava.exception.NotFoundException} type
 * that corresponds to {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
 * when user in database not exists.
 *
 * @since 1.0
 * @author AlexVar
 */
public class UserNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = -8230750312895215676L;

  public UserNotFoundException(@Nonnull final String message) {
    super(message);
  }
}
