package com.alexvar.testAssignmentJava.exception.user;

import com.alexvar.testAssignmentJava.exception.BadRequestException;
import jakarta.annotation.Nonnull;
import java.io.Serial;

/**
 * Exception of {@link BadRequestException} type
 * that corresponds to {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity
 * when user age does not sufficient to use resource.
 *
 * @since 1.0
 * @author AlexVar
 */
public class NotSufficientUserAgeException extends BadRequestException {

  @Serial
  private static final long serialVersionUID = 4248973526995872332L;

  public NotSufficientUserAgeException(@Nonnull final String message) {
    super(message);
  }
}
