package com.hgyl.nonsan_message.service;

import com.hgyl.nonsan_message.data.entity.ReceiveMessage;

import java.util.List;


public interface MessageService {
    // 메시지 전송
    String send(ReceiveMessage message) throws Exception;

    // 수신 메시지 목록
    List<ReceiveMessage> receiveList(String receiveId) throws Exception;

    // 수신 메시지 상세보기
    ReceiveMessage receiveRead(Long id);

    // 휴지통 목록
    List<ReceiveMessage> deleteList(String receiveId) throws Exception;

    // 쪽지 휴지통으로 이동
    void moveTrash(long id);

}
