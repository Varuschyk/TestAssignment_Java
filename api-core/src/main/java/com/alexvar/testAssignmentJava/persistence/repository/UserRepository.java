package com.alexvar.testAssignmentJava.persistence.repository;

import com.alexvar.testAssignmentJava.persistence.entity.User;
import jakarta.annotation.Nonnull;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository that relates to {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(@Nonnull String email);
  boolean existsByEmail(@Nonnull String email);
  User deleteByEmail(@Nonnull String email);
  List<User> findUsersByBirthDateBetween(@Nonnull String from, @Nonnull String to);
}
