package com.alexvar.testAssignmentJava.persistence.entity;


import com.alexvar.testAssignmentJava.common.annotation.ValidEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Representation entity model as client with field to him identification.
 * <p>
 *   Uses as main source of client identification and used for various operations in application.
 * </p>
 *
 * @since 1.0
 * @author AlexVar
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ValidEmail
  @Column(name = "email")
  String email;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @Column(name = "birth_date")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  String birthDate;

  @Column(name = "address")
  String address;

  @Column(name = "phone_number")
  String phoneNumber;
}
