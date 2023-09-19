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

   /* @PostMapping("/join")
    public String userJoin(@RequestBody UserDto userDto){
        System.out.println(userDto);
        mainServerService.save(userDto);
        return "main";
    }*/

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

    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody UserDto userDto){
        System.out.println("로그인: server 1777");
        System.out.println(userDto);
        UserDto checkedUser = mainServerService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkedUser);
    }

    @PostMapping("/user/{uId}")
    public ResponseEntity<UserDto> userInfo(@PathVariable String uId){
        UserDto info = mainServerService.findByUId(uId);
        return ResponseEntity.status(HttpStatus.OK).body(info);

    }
}
