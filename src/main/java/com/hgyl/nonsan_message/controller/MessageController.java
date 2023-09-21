package com.hgyl.nonsan_message.controller;

import com.hgyl.nonsan_message.data.dto.ResponseDTO;
import com.hgyl.nonsan_message.data.dto.UserDto;
import com.hgyl.nonsan_message.data.entity.ReceiveMessage;
import com.hgyl.nonsan_message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    HttpSession session;

    private Map<String, String> userList = new HashMap<>();

    //유저 정보 받아오기
    @PostMapping("/receivelist/user/valid")
    @ResponseBody
    public void checkUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println(userDto);
        if(userDto == null){
            throw new Exception();
        }
        userList.put(userDto.getUid(), userDto.getNickName());
    }

    //채팅리스트 화면
    @GetMapping("/receivelist")
    public String rooms(@RequestParam String uid, Model model) throws Exception {
        session.setAttribute("sendId", uid);

        System.out.println(uid);

        model.addAttribute("list", messageService.receiveList(uid));

        return "/message/receiveList";
    }

    // 메시지 전송 폼으로 이동
    @GetMapping("/message/send")
    public String sendForm() {

        return "message/send";
    }

    // 메시지 전송 처리
    @PostMapping("/send")
    public String send(ResponseDTO message) throws Exception {

        String id = message.getSendId();

        System.out.println(id);

        // 전송 값 테스트 콘솔 출력
        System.out.println("발신아이디: " +message.getSendId());
        System.out.println("수신아이디: "+message.getReceiveId());
        System.out.println("발신제목: " +message.getTitle());
        System.out.println("발신내용: " +message.getContent());

        // 반환값 아직 안 줬습니다.. (예외처리 미 구현..)
        String result = messageService.send(message);

        return "redirect:/receivelist?uid="+id;
    }

    // 수신 메시지 목록
    /*@GetMapping("/receivelist")
    public String receiveList(Model model) throws Exception{

        model.addAttribute("list", messageService.receiveList("two"));

        return "/message/receiveList";
    }*/

    // 수신 메시지 상세조회
    @GetMapping("/receiveRead")
    public String receiveRead(Model model, Long id){
        model.addAttribute("receive",messageService.receiveRead(id));
        return "/message/receiveRead";
    }

    /*// 휴지통 목록 조회
    @GetMapping("/deletelist")
    public String deleteList(Model model) throws Exception{

        model.addAttribute("list", messageService.deleteList("two"));

        return "/message/deleteList";
    }

    // 상세 글 -> 휴지통으로 이동
    @GetMapping("/moveTrash")
    public String moveTrash(Long id) {
        messageService.moveTrash(id);

        return "redirect:/deletelist";
    }*/
}
