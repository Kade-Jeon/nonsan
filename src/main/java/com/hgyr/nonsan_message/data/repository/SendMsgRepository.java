package com.hgyr.nonsan_message.data.repository;

import com.hgyr.nonsan_message.data.entity.SendMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMsgRepository extends JpaRepository<SendMessage, Long> {

    List<SendMessage> findAllBySendIdAndDeleteStatus(String sendId, boolean deleteStatus, Sort sort);
}
