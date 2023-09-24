package com.hgyr.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {

    /* 메인페이지로 연결 */
    @GetMapping
    public String home() {
        return "redirect:/hgyr";
    }
}
