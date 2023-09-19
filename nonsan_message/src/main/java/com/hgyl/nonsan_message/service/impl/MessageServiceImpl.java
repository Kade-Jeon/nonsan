package com.hgyl.nonsan_message.service.impl;

import com.hgyl.nonsan_message.data.repository.MessageRepository;
import com.hgyl.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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





}
