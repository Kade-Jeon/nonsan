package com.hgyl.nonsan_message.service;

import com.hgyl.nonsan_message.data.dto.SendDTO;
import com.hgyl.nonsan_message.data.entity.Message;
import org.springframework.stereotype.Service;


public interface MessageService {
    public String send(Message message) throws Exception;

}
