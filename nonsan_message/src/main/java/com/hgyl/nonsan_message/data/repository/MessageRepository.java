package com.hgyl.nonsan_message.data.repository;

import com.hgyl.nonsan_message.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository 작동을 위한 클래스
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
