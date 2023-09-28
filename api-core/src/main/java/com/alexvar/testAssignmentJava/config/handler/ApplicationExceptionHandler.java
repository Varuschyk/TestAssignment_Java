package com.alexvar.testAssignmentJava.config.handler;

import com.alexvar.testAssignmentJava.common.util.ExceptionMessage;
import com.alexvar.testAssignmentJava.common.util.ExceptionWebSupport;
import com.alexvar.testAssignmentJava.exception.BadRequestException;
import com.alexvar.testAssignmentJava.exception.NotFoundException;
import com.alexvar.testAssignmentJava.model.pojo.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Main exception handler that has inner handlers to catch all errors by http status.
 * <p>
 *   Each inner handler catches each error by his error status, and providing special
 *   error message from {@link ExceptionMessage}.
 *   For simplify, is using {@link ExceptionWebSupport} that provides simple methods,
 *   that allows get needed information fast and convenient.
 * </p>
 *
 * @see ExceptionMessage
 * @see ExceptionWebSupport
 * @since 1.0
 * @author AlexVar
 */
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

  private final ExceptionMessage exceptionMessage;
  private final ExceptionWebSupport exceptionWebSupport;

  /**
   * Handles {@link NotFoundException} when desired resources wasn't found.
   *
   * @param notFoundException occurred exception of {@link NotFoundException} type.
   * @return {@link ResponseEntity<ErrorMessage>} information of exception that will display in JSON format.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(@NonNull final NotFoundException notFoundException) {
    log.error("Exception was thrown because the resource was not found.", notFoundException);
    final var responseMessage = exceptionMessage.getNotFoundExceptionMessage();
    return exceptionWebSupport.getErrorResponse(notFoundException, HttpStatus.NOT_FOUND, responseMessage);
  }

  /**
   * Handles {@link BadRequestException} when requested resource get bad data.
   *
   * @param badRequestException occurred exception of {@link BadRequestException} type.
   * @return {@link ResponseEntity<ErrorMessage>} information of exception that will display in JSON format.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessage> handleBadRequestException(@NonNull final BadRequestException badRequestException) {
    log.error("Exception was thrown by bad request.", badRequestException);
    final var responseMessage = exceptionMessage.getBadRequestExceptionMessage();
    return exceptionWebSupport.getErrorResponse(badRequestException, HttpStatus.BAD_REQUEST, responseMessage);
  }

  /**
   * Handles {@link ConstraintViolationException} when provided data was failed on validation phase.
   *
   * @param validationException occurred exception of {@link ValidationException} type.
   * @return {@link ResponseEntity<ErrorMessage>} information of exception that will display in JSON format.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorMessage> handleResourceNotFoundException(@NonNull final ValidationException validationException) {
    log.error("Exception was thrown because validation error occurred.", validationException);
    final var responseMessage = exceptionMessage.getValidationExceptionMessage();
    return exceptionWebSupport.getErrorResponse(validationException, HttpStatus.BAD_REQUEST, responseMessage);
  }
}