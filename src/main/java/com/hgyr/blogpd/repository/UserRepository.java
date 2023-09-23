package com.hgyr.blogpd.repository;

import com.hgyr.blogpd.model.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBlog, Long> {
    // SELETE * FROM user WHERE username = 1?;
    Optional<UserBlog> findByUid(String uid);
}
