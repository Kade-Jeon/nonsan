package com.hgyl.nonsan_message.service.impl;

import com.hgyl.nonsan_message.data.dto.SendDTO;
import com.hgyl.nonsan_message.data.entity.Message;
import com.hgyl.nonsan_message.data.repository.MessageRepository;
import com.hgyl.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

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


    @Override
    public String send(Message message) throws Exception {
        LocalDateTime sendDate = LocalDateTime.now();
        message.setSendDate(sendDate);
        message.setSendId("one");
        message.setUid("one");


        messageRepository.save(message);


        return null;
    }
}
