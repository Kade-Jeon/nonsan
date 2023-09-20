package com.hgyl.nonsan_message.service;

import com.hgyl.nonsan_message.data.entity.Message;

import java.util.List;


public interface MessageService {
    String send(Message message) throws Exception;

    List<Message> receiveList(String receiveId) throws Exception;

    Message receiveRead(Long id);

}
