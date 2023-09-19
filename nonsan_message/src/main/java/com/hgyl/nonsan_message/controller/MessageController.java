package com.hgyl.nonsan_message.controller;

import com.hgyl.nonsan_message.data.dto.SendDTO;
import com.hgyl.nonsan_message.data.entity.Message;
import com.hgyl.nonsan_message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/message/send")
    public String sendForm() {
        return "message/send";
    }

    @PostMapping("/send")
    public ModelAndView send(SendDTO sendDTO) throws Exception {


        return null;
    }

}
