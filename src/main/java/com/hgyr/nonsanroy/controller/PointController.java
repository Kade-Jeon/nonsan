package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.bet.PointDto;
import com.hgyr.nonsanroy.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PointController {

	@Autowired
	PointService pointService;

	// 메인 페이지 - 충전
	@GetMapping("/mypage")
	public String myPoint(@Valid Model model, HttpSession session) {

		String userId = "uid1";
		session.setAttribute("userId", userId);

		String storedUserId = (String) session.getAttribute("userId");
		if (storedUserId == null) {
			// 세션에 userId가 없을 경우 처리
		}
		PointDto myPoint = pointService.myPoint(userId);

		model.addAttribute("myPoint", myPoint);

		return "mypage/pointRecharge";
	}

	// 충전 처리 메서드
	@PostMapping("/mypage/recharge")
	public String rechargePoint(@ModelAttribute @Valid PointDto myPointDTO, HttpSession session) {

		String userId = "uid1";
		session.setAttribute("userId", userId);

		String storedUserId = (String) session.getAttribute("userId");
		if (storedUserId == null) {
			// 세션에 userId가 없을 경우 처리
		}
		pointService.recharge(myPointDTO);
		return "redirect:/mypage/";
	}

	// 입금 페이지
	@GetMapping("/mypage/depositPage")
	public String deposit(@Valid Model model, HttpSession session) {

		String userId = "uid1";
		session.setAttribute("userId", userId);

		String storedUserId = (String) session.getAttribute("userId");
		if (storedUserId == null) {
			// 세션에 userId가 없을 경우 처리
		}
		PointDto deposit = pointService.deposit(userId);
		model.addAttribute("deposit", deposit);

		return "mypage/pointDeposit";
	}


	// 출금 페이지
	@GetMapping("/mypage/withdrawPage")
	public String withdrawPage(Model model, HttpSession session) {

		String userId = "uid1";
		session.setAttribute("userId", userId);

		String storedUserId = (String) session.getAttribute("userId");
		if (storedUserId == null) {
			// 세션에 userId가 없을 경우 처리
		}
		Double currentPoint = pointService.currentPoint(userId); // 현재 보유 머니 조회
		PointDto withdraw = pointService.withdrawPage(userId);

		model.addAttribute("currentPoint", currentPoint);
		model.addAttribute("withdraw", withdraw);

		return "mypage/pointWithdraw";
	}

	// 출금 처리 메서드
	@PostMapping("/mypage/withdraw")
	public String withdraw(@ModelAttribute @Valid PointDto myPointDTO, Model model, HttpSession session) {

		String userId = "uid1";
		session.setAttribute("userId", userId);

		String storedUserId = (String) session.getAttribute("userId");
		if (storedUserId == null) {
			// 세션에 userId가 없을 경우 처리
		}
		Double currentPoint = pointService.currentPoint(userId);

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