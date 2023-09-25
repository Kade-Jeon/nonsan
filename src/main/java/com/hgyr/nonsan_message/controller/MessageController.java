package com.hgyr.nonsan_message.controller;

import com.hgyr.nonsan_message.data.dto.ResponseDTO;
import com.hgyr.nonsan_message.data.dto.UserDto;
import com.hgyr.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class MessageController {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    HttpSession session;

    // 다른 서버에서 넘어올 때 유저 정보 조회하고 메인으로 넘깁니다.
    // hgyr Main -> messageMain(receiveList 으로 이동 )
    @GetMapping("/receivelist")
    public String receiveList(HttpSession session, Model model) throws Exception {

        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/" + uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        //model.addAttribute("uid", responseEntity.getBody().getUid());
        model.addAttribute("list", messageService.receiveList(responseEntity.getBody().getUid()));
        LOGGER.info("[Port:2113] MessageContorller");
        return "/message/receivelist";
    }

    // 수신 메시지 상세조회
    @GetMapping("/receiveRead")
    public String receiveRead(Model model, Long id){
        LOGGER.info(session.getId());
        model.addAttribute("receive",messageService.receiveRead(id));
        return "/message/receiveRead";
    }

    // 메시지 전송 폼으로 이동
    @GetMapping("/message/send")
    public String sendForm() {

        return "message/send";
    }

    // 메시지 전송 처리
    @PostMapping("/send")
    public String send(ResponseDTO message, HttpSession session) throws Exception {

        String sendId = (String) session.getAttribute("user");

        // 전송 값 테스트 콘솔 출력 (
        System.out.println(sendId);
        System.out.println("발신아이디: " +message.getSendId());
        System.out.println("수신아이디: "+message.getReceiveId());
        System.out.println("발신제목: " +message.getTitle());
        System.out.println("발신내용: " +message.getContent());

        String status = messageService.send(message);
        System.out.println(status);
        String link = null;

        if ("true".equals(status)) {
            link = "redirect:/receivelist";
        } else {
            link = "redirect:/message/error/error";
        }

        return link;
    }

    // 발신 쪽지함 목록
    @GetMapping("/sendlist")
    public String sendList(HttpSession session, Model model) throws Exception {
        System.out.println(session.getId());

        String uid = (String) session.getAttribute("user");

        model.addAttribute("list", messageService.sendList(uid));

        return "/message/sendlist";
    }

    // 발신 쪽지 상세조회
    @GetMapping("/sendRead")
    public String sendRead(Model model, Long id) {
        model.addAttribute("send", messageService.SendRead(id));
        return "/message/sendRead";
    }

    // 수신목록에서 선택 삭제
    @PostMapping("/receiveDelete")
    public ResponseEntity<String> receiveDelete(@RequestBody List<Long> ids){
        messageService.receiveDelete(ids);
        return ResponseEntity.status(HttpStatus.OK).body("메시지가 삭제되었습니다.");
    }

    // 발신목록에서 선택 삭제
    @PostMapping("/sendDelete")
    public ResponseEntity<String> sendDelete(@RequestBody List<Long> ids) {
        messageService.sendDelete(ids);
        return ResponseEntity.status(HttpStatus.OK).body("메시지가 삭제되었습니다");
    }

    // 발신 아이디 유효성 체크
    /*@PostMapping("/checkId")
    public ResponseEntity<String> checkId(@RequestBody String id) {
        System.out.println(id);
        boolean duplicatedId = messageService.duplicatedId(id);
        String result;
        if (duplicatedId == false) {
            result = "not_usable";
        } else {
            result = "usable";
        }
        return ResponseEntity.ok(result);
    }*/

}
