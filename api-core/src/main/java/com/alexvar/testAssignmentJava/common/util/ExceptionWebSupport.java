package com.alexvar.testAssignmentJava.common.util;

import com.alexvar.testAssignmentJava.model.pojo.ErrorMessage;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Util class for providing methods for simplify getting some information according
 * occurred exception by making requests.
 *
 * @see ErrorMessage
 * @see ExceptionMessage
 * @since 1.0
 * @author AlexVar
 */
@Component
public class ExceptionWebSupport {

  /**
   * Provides http status of exception response of occurred exception.
   * <p>
   *   Gets http status from provided exception and checks, if exception
   *   has annotation {@link ResponseStatus} above the class.
   *   If annotation doesn't exist in this exception, than will be used
   *   backup parameter.
   * </p>
   *
   * @param exception occurred exception from request.
   * @param fallBackHttpStatus provided backup http status that corresponds exception.
   * @return {@link HttpStatus} of exception that was occurred.
   */
  public HttpStatus getHttpStatus(@Nonnull final Exception exception,
                                  @Nonnull final HttpStatus fallBackHttpStatus) {
    final var responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
    return responseStatus != null ? responseStatus.value() : fallBackHttpStatus;
  }

  /**
   * Provides response entity of {@link ErrorMessage} to display it in JSON format.
   * <p>
   *   All information puts into one {@link ResponseEntity<ErrorMessage>} response and
   *   will be shown in JSON format from service response on that was sent request.
   * </p>
   *
   * @param exception occurred exception from request.
   * @param fallBackHttpStatus provided backup http status that corresponds exception.
   * @param responseMessage message of exception that corresponds exception status.
   * @return {@link ResponseEntity<ErrorMessage>} information about occurred exception.
   */
  public ResponseEntity<ErrorMessage> getErrorResponse(@NonNull final Exception exception,
                                                       @Nonnull final HttpStatus fallBackHttpStatus,
                                                       @Nonnull final String responseMessage) {
    final var httpStatus = getHttpStatus(exception, fallBackHttpStatus);
    final var message = getErrorMessage(httpStatus, responseMessage);
    return new ResponseEntity<>(message, httpStatus);
  }

  /**
   * Provides error message that corresponds exception http status.
   *
   * @param httpStatus http status from occurred exception.
   * @param responseMessage message from occurred exception.
   * @return {@link ErrorMessage} object that stores all information about error.
   */
  public ErrorMessage getErrorMessage(@Nonnull final HttpStatus httpStatus,
                                      @Nonnull final String responseMessage) {
    return ErrorMessage.builder().status(httpStatus.value()).description(responseMessage).build();
  }
}