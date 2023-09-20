package com.hgyr.chatting.repository;

import com.hgyr.chatting.data.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatroomMap;

    @PostConstruct
    private void init() {
        chatroomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        List chatRooms = new ArrayList(chatroomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatroomMap.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatroomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
