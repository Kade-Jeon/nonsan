package com.hgyr.chatting.controller;

import com.hgyr.chatting.data.ChatMessage;
import com.hgyr.chatting.data.ChatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("chat/message")
    public void message(ChatMessage message){
        if (ChatMessage.MessageType.JOIN.equals(message.getType())) {
            message.setMessage(message.getSender() + " 님이 입장하셨습니다.");
        }
        messageTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
