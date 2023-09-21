package com.hgyr.chatting.controller;

import com.hgyr.chatting.data.ChatRoom;
import com.hgyr.chatting.data.UserDto;
import com.hgyr.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private Map<String, String> userList = new HashMap<>();

    @Autowired
    private HttpSession userSession;

    //유저 정보 받아오기
    @PostMapping("/room/user/valid")
    @ResponseBody
    public void checkUser(@RequestBody UserDto userDto) throws Exception {
        if(userDto == null){
            throw new Exception();
        }
        userList.put(userDto.getUid(), userDto.getNickName());
    }

    //채팅리스트 화면
    @GetMapping("/room")
    public String rooms(@RequestParam String uid, Model model) {
        String nick = "";
        while (!userList.isEmpty()) {
            Set<String> uidList = userList.keySet();
            if(uidList.contains(uid)){
                nick = userList.get(uid);
                break;
            }
            }
        model.addAttribute("uid", uid);
        model.addAttribute("nickName", nick);
       return "/chat/room";
            }





    //모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    //채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    //채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    //특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
