package com.alexvar.testAssignmentJava.common.constants.validator;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

/**
 * Utility class with constants for {@link com.alexvar.testAssignmentJava.common.validator.EmailValidator}.
 *
 * @since 1.0
 * @author AlexVar
 */
@UtilityClass
public final class EmailValidatorConstants {
  public final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  public final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
}