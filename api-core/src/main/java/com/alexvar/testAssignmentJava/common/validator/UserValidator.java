package com.alexvar.testAssignmentJava.common.validator;

import com.alexvar.testAssignmentJava.common.annotation.ValidEmail;
import com.alexvar.testAssignmentJava.common.constants.DateFormatConstants;
import com.alexvar.testAssignmentJava.common.constants.validator.EmailValidatorConstants;
import jakarta.annotation.Nonnull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Validator class to validate an {@link com.alexvar.testAssignmentJava.persistence.entity.User}
 * entity parameters.
 *
 * @see DateFormatConstants
 * @since 1.0
 * @author AlexVar
 */
@Component
public class UserValidator {

  @Value("${user.properties.allowed-age}")
  private int allowedUserAge;

  public boolean canUseServiceByGivenAge(@Nonnull final String dateString) {
    try {
      final var date = new SimpleDateFormat(DateFormatConstants.DATE_FORMAT_PATTERN).parse(dateString);
      final var calendar = GregorianCalendar.getInstance();
      calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - allowedUserAge);
      return calendar.getTime().after(date);
    } catch (final ParseException parseException) {
      throw new RuntimeException("Wrong date format was specified. Unable to parse date!");
    }
  }
}
