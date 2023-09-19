package com.hgyr.chatting.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*DTO*/
@Getter
@Setter
@ToString
public class ChatMessage {
    public enum MessageType{
        ENTER,
        TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
