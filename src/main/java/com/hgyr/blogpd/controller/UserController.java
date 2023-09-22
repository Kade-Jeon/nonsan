package com.hgyr.blogpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 /이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/**
@Controller
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


    /*private Set<Map<String, String>> userList = new HashSet<>();

    //유저 정보 받아오기
    @PostMapping("/user/valid")
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
    }*/
}
