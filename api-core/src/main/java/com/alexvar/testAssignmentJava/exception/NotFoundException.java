package com.alexvar.testAssignmentJava.exception;

import jakarta.annotation.Nonnull;
import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that responses {@link HttpStatus#NOT_FOUND} status when resources wasn't found.
 *
 * @since 1.0
 * @author AlexVar
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6202261537399994737L;

  public NotFoundException(@Nonnull final String message) {
    super(message);
  }
}
