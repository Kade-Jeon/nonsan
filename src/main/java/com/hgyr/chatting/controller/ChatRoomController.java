package com.hgyr.chatting.controller;

import com.hgyr.chatting.data.ChatRoom;
import com.hgyr.chatting.data.UserDto;
import com.hgyr.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
    private final ChatRoomRepository chatRoomRepository;
    private Map<String, String> userList = new HashMap<>();
   // private HttpSession serviceSession;


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

    //다른 서버에서 넘어올 때 유저 세션 정보 조회하고 메인으로 넘깁니다.
    @GetMapping("/room")
    public String rooms(HttpSession session, Model model){

        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        UserDto rcvdDto = responseEntity.getBody();
        model.addAttribute("nickName", responseEntity.getBody().getNickName());
        logger.info("[Port:1999] ChatRoomContorller");
        return "/chat/room";
    }
}
