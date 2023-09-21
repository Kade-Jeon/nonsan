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
        // 임의로 넣은 발신자 아이디
        message.setSendId("one");
        message.setUid("one");

        messageRepository.save(message);

        return null;
    }

    // 로그인 후 수신 메시지 조회
    @Override
    public List<Message> receiveList(String receiveId) throws Exception {
        Boolean deleteStatus = false;

        return messageRepository.findAllByReceiveIdAndDeleteStatus(receiveId, deleteStatus);
    }

    // 수신 메시지 목록 -> 수신 메시지 상세조회
    @Override
    public Message receiveRead(Long id) {
        return messageRepository.findById(id).get();
    }

    // 휴지통 목록
    @Override
    public List<Message> deleteList(String receiveId) throws Exception {
        Boolean deleteStatus = true;

        return messageRepository.findAllByReceiveIdAndDeleteStatus(receiveId, deleteStatus);
    }

    // 글 -> 휴지통 목록으로 이동
    @Override
    public void moveTrash(long id) {
        Message message = messageRepository.findById(id).get();

        message.setDeleteStatus(true);
        messageRepository.save(message);
    }


}
