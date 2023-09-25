package com.hgyr.nonsan_message.service.impl;

import com.hgyr.nonsan_message.data.dto.ResponseDTO;
import com.hgyr.nonsan_message.data.entity.ReceiveMessage;
import com.hgyr.nonsan_message.data.entity.SendMessage;
import com.hgyr.nonsan_message.data.repository.ReceiveMsgRepository;
import com.hgyr.nonsan_message.data.repository.SendMsgRepository;
import com.hgyr.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // 로그인 후 수신 메시지 목록 조회
    @Override
    public List<ReceiveMessage> receiveList(String msgId) throws Exception {

        return messageRepository.findAllByReceiveIdAndArchiveStatus(msgId, false, Sort.by(Sort.Direction.DESC, "sendDate"));
    }

    // 수신 메시지 상세조회
    @Override
    public ReceiveMessage receiveRead(Long id) {
        return messageRepository.findById(id).get();
    }

    // 메시지 발신 DB 기록
    @Override
    public String send(ResponseDTO message) throws Exception {
        try{
            // 발신 DB에 저장
            SendMessage sendMessage = message.toSend();
            sendMsgRepository.save(sendMessage);
            // 수신 DB에 저장
            ReceiveMessage receiveMessage = message.toReceive();
            messageRepository.save(receiveMessage);
        }catch(Exception e){
            System.out.println("발신 Repository 기록 실패");
            return "false";
        }


        return "true";
    }

    // 발신 메시지 목록
    @Override
    public List<SendMessage> sendList(String sendId) throws Exception {

        return sendMsgRepository.findAllBySendIdAndArchiveStatus(sendId, false, Sort.by(Sort.Direction.DESC, "sendDate"));
    }

    // 발신 메시지 상세조회
    @Override
    public SendMessage SendRead(Long id) {
        return sendMsgRepository.findById(id).get();
    }

    // 수신 메시지 삭제
    @Override
    public void receiveDelete(List<Long> ids) {
        for (Long id : ids) {
            messageRepository.deleteById(id);
        }
    }

    // 발신 메시지 삭제
    @Override
    public void sendDelete(List<Long> ids) {
        for (Long id : ids) {
            sendMsgRepository.deleteById(id);
        }
    }


}
