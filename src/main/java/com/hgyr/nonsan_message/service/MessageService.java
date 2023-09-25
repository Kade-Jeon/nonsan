package com.hgyr.nonsan_message.service;

import com.hgyr.nonsan_message.data.dto.ResponseDTO;
import com.hgyr.nonsan_message.data.entity.ReceiveMessage;
import com.hgyr.nonsan_message.data.entity.SendMessage;

import java.util.List;


public interface MessageService {
    // 메시지 전송
    String send(ResponseDTO message) throws Exception;

    // 수신 메시지 목록
    List<ReceiveMessage> receiveList(String uId) throws Exception;

    // 수신 메시지 상세보기
    ReceiveMessage receiveRead(Long id);

    // 발신 메시지 목록
    List<SendMessage> sendList(String uId) throws Exception;

    // 발신 메시지 상세보기
    SendMessage SendRead(Long id);

    // 수신 메시지 삭제
    void receiveDelete(List<Long> ids);

    // 발신 메시지 삭제
    void sendDelete(List<Long> ids);


}
