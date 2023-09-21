package com.hgyl.nonsan_message.service.impl;

import com.hgyl.nonsan_message.data.dto.ResponseDTO;
import com.hgyl.nonsan_message.data.entity.ReceiveMessage;
import com.hgyl.nonsan_message.data.entity.SendMessage;
import com.hgyl.nonsan_message.data.repository.ReceiveMsgRepository;
import com.hgyl.nonsan_message.data.repository.SendMsgRepository;
import com.hgyl.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageService 인터페이스를 상속받은 클래스
 *
 ***/
@Service
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private ReceiveMsgRepository messageRepository;
    private SendMsgRepository sendMsgRepository;

    @Autowired
    public MessageServiceImpl(ReceiveMsgRepository messageRepository,
                              SendMsgRepository sendMsgRepository) {
        this.messageRepository = messageRepository;
        this.sendMsgRepository = sendMsgRepository;

    }

    // 메시지 발신 DB 기록
    @Override
    public String send(ResponseDTO message) throws Exception {
        SendMessage sendMessage = message.toSend();
        sendMsgRepository.save(sendMessage);

        ReceiveMessage receiveMessage = message.toReceive();
        messageRepository.save(receiveMessage);

        /*messageRepository.save(message);
        sendMsgRepository.save(message);*/

        return null;
    }

    // 로그인 후 수신 메시지 조회
    @Override
    public List<ReceiveMessage> receiveList(String receiveId) throws Exception {
        Boolean deleteStatus = false;

        return messageRepository.findAllByReceiveIdAndDeleteStatus(receiveId, deleteStatus);
    }

    // 수신 메시지 목록 -> 수신 메시지 상세조회
    @Override
    public ReceiveMessage receiveRead(Long id) {
        return messageRepository.findById(id).get();
    }

    /*// 휴지통 목록
    @Override
    public List<ReceiveMessage> deleteList(String receiveId) throws Exception {
        Boolean deleteStatus = true;

        return messageRepository.findAllByReceiveIdAndDeleteStatus(receiveId, deleteStatus);
    }

    // 글 -> 휴지통 목록으로 이동
    @Override
    public void moveTrash(long id) {
        ReceiveMessage message = messageRepository.findById(id).get();

        message.setDeleteStatus(true);
        messageRepository.save(message);
    }*/


}
