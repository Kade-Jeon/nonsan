package com.hgyr.nonsan_message.data.repository;

import com.hgyr.nonsan_message.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean findByUid(String uid);
    User findByUidAndPassword(String uid, String password);
}
