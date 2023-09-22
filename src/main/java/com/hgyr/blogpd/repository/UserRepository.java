package com.hgyr.blogpd.repository;

import com.hgyr.blogpd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // SELETE * FROM user WHERE username = 1?;
    Optional<User> findByUid(String username);
}
