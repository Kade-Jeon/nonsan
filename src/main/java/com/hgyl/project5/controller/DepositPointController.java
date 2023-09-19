package com.hgyl.project5.controller;

import com.hgyl.project5.dto.MyPointDTO;
import com.hgyl.project5.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@Controller
public class DepositPointController {

    @Autowired
    PointService pointService;

    @GetMapping("/mypage")
    public String myPoint(@Valid Model model) {

        Integer userId = 1;
        MyPointDTO myPoint = pointService.myPoint(userId);

        model.addAttribute("myPoint", myPoint);

        return "mypage/pointRecharge";
    }

    @PostMapping("/mypage/recharge")
    public String rechargePoint(@ModelAttribute @Valid MyPointDTO myPointDTO) {

        pointService.recharge(myPointDTO);
        return "redirect:/mypage/";
    }

    @GetMapping("/mypage/depositPage")
    public String deposit(@Valid Model model) {
        Integer userId = 1;
        MyPointDTO deposit = pointService.deposit(userId);
        model.addAttribute("deposit", deposit);

        return "mypage/pointDeposit";
    }

    @GetMapping("/mypage/withdrawPage")
    public String withdrawPage(Model model) {
        Integer userId = 1;

        int currentPoint = pointService.currentPoint(userId); // 현재 보유 머니 조회
        MyPointDTO withdraw = pointService.withdrawPage(userId);

        model.addAttribute("currentPoint", currentPoint);
        model.addAttribute("withdraw", withdraw);

        return "mypage/pointWithdraw";
    }

    @PostMapping("/mypage/withdraw")
    public String withdraw(@ModelAttribute @Valid MyPointDTO myPointDTO, Model model) {
        Integer userId = 1;
        int currentPoint = pointService.currentPoint(userId);

        // 출금 금액이 현재 보유 머니를 초과하는지 검증
        if (myPointDTO.getMinusPoint() > currentPoint) {
            model.addAttribute("currentPoint", currentPoint);
            model.addAttribute("withdraw", myPointDTO);
            return "mypage/pointWithdraw";
        }

        pointService.withdraw(myPointDTO);
        return "redirect:/mypage/withdrawPage/";
    }

}


