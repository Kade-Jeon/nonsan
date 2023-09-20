package com.hgyl.nonsan_message.controller;

import com.hgyl.nonsan_message.data.dto.SendDTO;
import com.hgyl.nonsan_message.data.entity.Message;
import com.hgyl.nonsan_message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 메시지 전송 폼으로 이동
    @GetMapping("/message/send")
    public String sendForm() {
        return "message/send";
    }

    // 메시지 전송 처리
    @PostMapping("/send")
    public String send(Message message) throws Exception {
        System.out.println("수신아이디: "+message.getReceiveId());
        System.out.println("발신제목: " +message.getTitle());
        System.out.println("발신내용: " +message.getContent());

        String result = messageService.send(message);

        return "redirect:/receivelist";
    }

    // 수신 메시지 목록
    @GetMapping("/receivelist")
    public String receiveList(Model model) throws Exception{

        model.addAttribute("list", messageService.receiveList("two"));

        return "/message/receiveList";
    }

}
