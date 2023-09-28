package com.alexvar.testAssignmentJava.model.pojo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Model representation that stores detailed information about
 * error that was occurred by making requests to resources.
 *
 * @since 1.0
 * @author AlexVar
 */
@EqualsAndHashCode
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

  int status;

  String description;
}

