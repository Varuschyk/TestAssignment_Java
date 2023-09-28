package com.alexvar.testAssignmentJava.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representation of {@link com.alexvar.testAssignmentJava.persistence.entity.User} entity.
 * <p>
 *   Uses for request bodies when need to send entity data to the service.
 *   And for response bodies when client wait data the response from the services.
 * </p>
 *
 * @see com.alexvar.testAssignmentJava.persistence.entity.User
 * @since 1.0
 * @author AlexVar
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

  @NotBlank
  @JsonProperty(value = "email")
  String email;

  @NotBlank
  @JsonProperty(value = "firstName")
  String firstName;

  @NotBlank
  @JsonProperty(value = "lastName")
  String lastName;

  @NotNull
  @JsonProperty(value = "birthDate")
  String birthDate;

  @Nullable
  @JsonProperty(value = "address")
  String address;

  @Nullable
  @JsonProperty(value = "phoneNumber")
  String phoneNumber;
}
