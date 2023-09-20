package com.hgyr.chatting.controller;

import com.hgyr.chatting.data.ChatRoom;
import com.hgyr.chatting.data.UserDto;
import com.hgyr.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Set<Map<String, String>> userList = new HashSet<>();

    @Autowired
    private HttpSession userSession;

    //유저 정보 받아오기
    @PostMapping("/valid")
    @ResponseBody
    public void checkUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println(userDto);
        if(userDto == null){
            throw new Exception();
        }
        Map<String, String> temp = new HashMap<>();
        temp.put(userDto.getUid(), userDto.getNickName());
        System.out.println(temp);
        userList.add(temp);
    }


    //채팅리스트 화면
    @GetMapping("/room")
    public String rooms(@RequestParam String uid, Model model) {
        System.out.println(uid);
        String nick = null;
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            Map<String,String> map = (Map<String, String>) iterator.next();
            System.out.println(map);
            if(!map.get(uid).isBlank()){
                nick = map.get(uid);
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
