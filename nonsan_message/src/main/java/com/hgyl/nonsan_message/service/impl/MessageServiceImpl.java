package com.hgyl.nonsan_message.service.impl;

import com.hgyl.nonsan_message.data.entity.Message;
import com.hgyl.nonsan_message.data.repository.MessageRepository;
import com.hgyl.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * MessageService 인터페이스를 상속받은 클래스
 *
 ***/
@Service
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 메시지 발신 DB 기록
    @Override
    public String send(Message message) throws Exception {

        message.setSendId("one");
        message.setUid("one");

        messageRepository.save(message);

        return null;
    }

    // 로그인 후 수신 메시지 조회
    @Override
    public List<Message> receiveList(String receiveId) throws Exception {

        return messageRepository.findAllByReceiveId(receiveId);
    }
}
