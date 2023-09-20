package com.hgyl.nonsan_message.service;

import com.hgyl.nonsan_message.data.dto.SendDTO;
import com.hgyl.nonsan_message.data.entity.Message;
import org.springframework.stereotype.Service;


public interface MessageService {
    String send(Message message) throws Exception;

    Message receiveList(String receiveId) throws Exception;

}
