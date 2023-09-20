package com.hgyr.multi.controller;

import com.hgyr.multi.dto.UserDto;
import com.hgyr.multi.service.MainRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/hgyr")
public class mainController {

    @Autowired
    HttpSession session;

    MainRestService mainRestService;

    @Autowired
    public mainController(MainRestService mainRestService) {
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

    /*각 서비스 누를 시 각 서비스로 이동*/
    @GetMapping("/service/{app}")
    public String moveToService(@PathVariable String app, Model model) {
        model.addAttribute("appName",app);
        System.out.println(app);
        return "app";
    }

    /*회원가입 페이지에서 정보 입력 후 제출하면 서버로 전송 후 결과리턴
    * 메인으로 이동되면 정상완료*/
    @PostMapping("/join")
    public String save(UserDto userDto){
        String result = mainRestService.postSave(userDto);
        System.out.println("회원가입 : 서버 1888");
        if(result.equals("main")){
            return "redirect:/hgyr";
        }
        return "error";
    }

    /*로그인 내용 입력 후, 서버로 전송 후 결과 리턴
    * 로그인 완료되면 메인페이지로 이동, 세션 정보 저장
    * 실패하면 에러 페이지로 이동*/
    @PostMapping("/login")
    public String login(UserDto userDto){
        UserDto userInfo = mainRestService.login(userDto);
        System.out.println("로그인 : 서버 1888");
        if(userInfo != null){
            System.out.println(userInfo);
            session.setAttribute("user", userInfo);
            return "redirect:/hgyr";
        }
        return "error";
    }
    /*로그아웃 클릭시 세션 삭제 후 메인페이지로 이동*/
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/hgyr";
    }
}
