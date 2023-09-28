package com.alexvar.testAssignmentJava.common.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Holder for containing special exception messages to provide
 * information in response form services.
 * <p>
 *   Exception messages stores in special `*.properties` file
 *   that specified in {@link PropertySource} annotation.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Getter
@Component
@PropertySource("classpath:exception-messages.properties")
public class ExceptionMessage {

  @Value("${not.found.exception.message}")
  private String notFoundExceptionMessage;

  @Value("${validation.exception.message}")
  private String validationExceptionMessage;

  @Value("${bad.request.exception.message}")
  private String badRequestExceptionMessage;
}
