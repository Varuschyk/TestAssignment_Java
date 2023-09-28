package com.alexvar.testAssignmentJava.common.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.alexvar.testAssignmentJava.common.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotation is used to verify that a given string value is a valid email address.
 * <p>
 *  The annotation is used by the {@link EmailValidator} to perform the actual validation.
 * </p>
 *
 * @see EmailValidator
 * @since 1.0
 * @author AlexVar
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

  /**
   * Accepts attribute, which specifies the error message to be shown if the email address is not valid.
   */
  String message() default "Email not valid";

  /**
   * Attributes for use in validation groups.
   */
  Class<?>[] groups() default {};

  /**
   * Attributes for use in validation payloads.
   */
  Class<? extends Payload>[] payload() default {};
}

