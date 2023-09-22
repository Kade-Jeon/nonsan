package com.hgyr.multi.mainServer.Repository;

import com.hgyr.multi.mainServer.data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
    User findByUidAndPassword(String uid, String password);
}
