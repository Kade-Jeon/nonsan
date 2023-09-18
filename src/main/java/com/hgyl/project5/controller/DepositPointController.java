package com.hgyl.project5.controller;

import com.hgyl.project5.dto.MyPointDTO;
import com.hgyl.project5.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepositPointController {

    @Autowired
    PointService pointService;

    @GetMapping("/mypage")
    public String myPoint(Model model) {

        Integer userId = 1;
        MyPointDTO myPoint = pointService.myPoint(userId);

        model.addAttribute("myPoint", myPoint);

        return "mypage/pointRecharge";
    }

    @PostMapping("/mypage/recharge")
    public String rechargePoint(@ModelAttribute MyPointDTO myPointDTO) {

        pointService.recharge(myPointDTO);
        return "redirect:/mypage/";
    }

    @GetMapping("/mypage/depositPage")
    public String deposit(Model model){
        Integer userId=1;
        MyPointDTO deposit = pointService.deposit(userId);
        model.addAttribute("deposit", deposit);

        return "mypage/pointDeposit";
    }

    @GetMapping("/mypage/withdrawPage")
    public String withdrawPage(Model model) {
        Integer userId=1;
        MyPointDTO withdraw = pointService.withdrawPage(userId);
        model.addAttribute("withdraw", withdraw);

        return "mypage/pointWithdraw";
    }

    @PostMapping("/mypage/withdraw")
    public String withdraw(@ModelAttribute MyPointDTO myPointDTO) {

        pointService.withdraw(myPointDTO);
        return "redirect:/mypage/withdrawPage/";
    }

}


