package com.hgyr.nonsan_message.controller;

import com.hgyr.nonsan_message.data.dto.ResponseDTO;
import com.hgyr.nonsan_message.data.dto.UserDto;
import com.hgyr.nonsan_message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class MessageController {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    HttpSession session;
    //private Map<String, String> userList = new HashMap<>();

    /*//유저 정보 받아오기
    @PostMapping("/receivelist/user/valid")
    @ResponseBody
    public void checkUser(@RequestBody UserDto userDto, HttpSession session) throws Exception {
        System.out.println(userDto);
        if(userDto == null) {
            throw new Exception();
        }
        userList.put(userDto.getUid(), userDto.getNickName());
        System.out.println("user 인포 : "+userDto.getUid());

    }

    // 메인페이지 (수신 쪽지 목록)
    @GetMapping("/receivelist")
    public String receiveList(Model model, HttpSession session) throws Exception {
        // 세션에서 유저 정보 가져오기
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        System.out.println(userDto);

        // 유저 정보가 없다면 처리할 내용 추가

        // 유저 정보가 있다면 처리
        String uid = userDto.getUid();

        // 새로운 ReceiveMessage 객체 생성 및 receiveId 설정
        ReceiveMessage receiveMessage = new ReceiveMessage();
        receiveMessage.setReceiveId(uid);

        model.addAttribute("list", messageService.receiveList(receiveMessage));

        return "/message/receiveList";
    }*/

    // 메인페이지 (수신 쪽지 목록)
    /*@GetMapping("/receivelist")
    public String receiveList(@RequestParam String uid, Model model) throws Exception {

        session.setAttribute("uid", uid);
        String msg = (String) session.getAttribute(uid);

        System.out.println(msg);

        model.addAttribute("list", messageService.receiveList(uid));

        return "/message/receiveList";
    }*/

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

    // 목록에서 선택 삭제


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
    }

    @PostMapping("/login")
    public String login(UserDto userDto, Model model){
        UserDto userInfo = mainRestService.login(userDto);
        if(userInfo != null){
            logger.info("[Port:1888][MainController] 로그인 : "+userDto);
            System.out.println(userInfo.getUid());
            session.setAttribute("user", userInfo.getUid()); //수정
            return "redirect:/hgyr";
        }
        return "error";
    }

    */
}
