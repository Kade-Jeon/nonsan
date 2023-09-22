package com.hgyr.blogpd.controller.api;

import com.hgyr.blogpd.dto.ResponseDto;
import com.hgyr.blogpd.model.User;
import com.hgyr.blogpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
//        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바 오브젝트를 JSON으로 변환 리턴 (Jackson)
//        return new ResponseDto<Integer>(200, 1); -> 200을 바로 넣기보다는 위의 코드처럼 하는게 좋음
//        return 1; // user.js 의 done(function (resp) 의 resp에 1이 리턴
    }

    /*!스프링 시큐리티 사용으로 인한 이전 로그인 구현 코드 주석처리!

    @Autowired // session 객체는 스프링 컨테이너가 빈으로 등록해서 가지고 있음
    private HttpSession session;

    // 파라미터로 HttpSession session 을 받아도 되고 (매개변수로 적기만 해도 DI를 해줌)
    // 위에 private 변수로 적어서 @Autowired 해줘도 된다.
    *//* 로그인 *//*
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController : login 호출됨");
        // principal : 접근 주체 (로그인이 완료되었으니 user 대신 변수명 변경)
        User principal = userService.로그인(user);

        if(principal != null) {
            session.setAttribute("principal", principal); // 세션이 생성
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // 세션을 생성하고 1을 반환하면 loginForm에서 user.js가 실행될 때, 로그인이 완료되면 메인화면으로 이동
        // 이 때 header가 무조건 호출 -> 헤더에 JSTL을 연결해서 로그인 관련 구현
    }*/
}
