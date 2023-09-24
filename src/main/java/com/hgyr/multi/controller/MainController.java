package com.hgyr.multi.controller;

import com.hgyr.multi.dto.UserDto;
import com.hgyr.multi.service.MainRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/hgyr")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

   @Autowired
    HttpSession session;

    MainRestService mainRestService;

    @Autowired
    public MainController(MainRestService mainRestService) {
        this.mainRestService = mainRestService;
    }
    
    /*최초 실행시 main으로 이동*/
    @GetMapping
    public String main(){
        return "main";
    }

    /*회원가입 누를 시 회원가입 페이지로 이동*/
    @GetMapping("/join")
    public String moveToJoin(){
        return "join";
    }

    /*로그인 누를 시 로그인 페이지로 이동*/
    @GetMapping("/login")
    public String moveToLogin() {
        return "login";
    }

   /* *//*각 서비스 누를 시 각 서비스로 이동*//*
    @GetMapping("/hgyr/service/{app}")
    public String moveToService(@PathVariable String app, Model model) {
        UserDto userInfo = (UserDto) session.getAttribute("user");
        int age = Period.between(userInfo.getBirthDate().toLocalDate(), LocalDate.now()).getYears();
        model.addAttribute("appName",app);
        model.addAttribute("age", age);
        return "app";
    }*/

    /*회원가입 페이지에서 정보 입력 후 제출하면 서버로 전송 후 결과리턴
    * 메인으로 이동되면 정상완료*/
    @PostMapping("/join")
    public String save(UserDto userDto){
        String result = mainRestService.postSave(userDto);
        logger.info("[Port:1888][MainController] 회원가입");
        if(result.equals("main")){
            return "redirect:/hgyr";
        }
        return "error";
    }

    /*로그인 내용 입력 후, 서버로 전송 후 결과 리턴
    * 로그인 완료되면 메인페이지로 이동, 세션 정보 저장
    * 실패하면 에러 페이지로 이동*/
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
    /*로그아웃 클릭시 세션 삭제 후 메인페이지로 이동*/
    @GetMapping("/logout")
    public String logout(HttpSession session){ //redis 세션 처리 위해 파라미터로 HttpSession을 받습니다.
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/hgyr";
    }


}
