package com.alexvar.testAssignmentJava.common.validator;

import com.alexvar.testAssignmentJava.common.annotation.ValidEmail;
import com.alexvar.testAssignmentJava.common.constants.validator.EmailValidatorConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NonNull;

/**
 * Validator class to validate an email address using a regular expression.
 * Implements the {@link ConstraintValidator} interface.
 *
 * @see ValidEmail
 * @see EmailValidatorConstants
 * @since 1.0
 * @author AlexVar
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

  @Override
  public boolean isValid(@NonNull final String email,
                         @NonNull final ConstraintValidatorContext context) {
    final var matcher = EmailValidatorConstants.EMAIL_PATTERN.matcher(email);
    return matcher.matches();
  }
}
