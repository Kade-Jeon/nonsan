package com.hgyl.nonsan_message.data.repository;

import com.hgyl.nonsan_message.data.entity.ReceiveMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository 작동을 위한 클래스
@Repository
public interface MessageRepository extends JpaRepository<ReceiveMessage, Long> {

    List<ReceiveMessage> findAllByReceiveIdAndDeleteStatus(String receiveId, boolean deleteStatus);

}
