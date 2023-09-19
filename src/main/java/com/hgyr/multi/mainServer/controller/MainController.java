package com.hgyr.multi.mainServer.controller;

import com.hgyr.multi.mainServer.data.dto.UserDto;
import com.hgyr.multi.mainServer.service.MainServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/valid")
public class MainController {

    MainServerService mainServerService;

    @Autowired
    public MainController(MainServerService mainServerService) {
        this.mainServerService = mainServerService;
    }

    /*main으로부터 정보 받아 회원가입 처리*/
    @PostMapping("/join")
    public ResponseEntity<String> userJoin(@RequestBody UserDto userDto){
        System.out.println("회원가입: server 1777");
        System.out.println(userDto);
        String result = mainServerService.save(userDto);
        if (result.equals("error")){
            return ResponseEntity.status(HttpStatus.OK).body("error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("main");
    }
    /*main으로부터 정보 받아 로그인 후, dto리턴*/
    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody UserDto userDto){
        System.out.println("로그인: server 1777");
        System.out.println(userDto);
        UserDto checkedUser = mainServerService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkedUser);
    }

    /*[테스트용] uid 요청하면 dto리턴*/
    @PostMapping("/user/{uId}")
    public ResponseEntity<UserDto> userInfo(@PathVariable String uId){
        UserDto info = mainServerService.findByUId(uId);
        return ResponseEntity.status(HttpStatus.OK).body(info);

    }
}
