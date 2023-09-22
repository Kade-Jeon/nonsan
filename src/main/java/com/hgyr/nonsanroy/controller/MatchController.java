package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.UserDto;
import com.hgyr.nonsanroy.data.dto.bet.BetCartDto;
import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.service.BetService;
import com.hgyr.nonsanroy.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 명원식
 * @classNote MatchController
 * @purpose
 */
@Controller
@RequestMapping("/bet")
public class MatchController {

	private final PointService pointService;
	private final BetService betService;

	@Autowired
	public MatchController(PointService pointService, BetService betService) {
		this.pointService = pointService;
		this.betService = betService;
	}

	/**
	 * @param model
	 * @methodNote navigateBetMain
	 * @purpose
	 * @author 명원식
	 */
	@GetMapping("/BetMain")
	public String navigateBetMain(Model model) {
		List<Match> matches = betService.getAllMatches();
		/**
		 * @declarationNote dateTimeFormatter
		 * @purpose appears as 2023-09-21T12:00 Prior to adding dates.format.
		 * Make use of thymeleaf: temporals.format in html
		 * <td th:text="${#temporals.format(match.matchStart, 'yyyy-MM-dd HH:mm')}"></td>
		 * <td th:text="${#temporals.format(match.matchEnd, 'yyyy-MM-dd HH:mm')}"></td>
		 * @author 명원식
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		model.addAttribute("matches", matches);
		return "/bet/BetMain"; // templates/bet/BetMain.html
	}

	/**
	 * @param cartData, betDto, model
	 * @methodNote navigateBetCart
	 * @purpose
	 * @otherNote Cannot use just @ModelAttribute while giving the tag to the map method in the Dto
	 * (Method Parameter Level vs Method Level)
	 * @author 명원식
	 */
	@PostMapping("/betCart")
	public String navigateBetCart(@ModelAttribute("getCartDataMap") BetCartDto cartData, BetDto betDto, Model model) {

		// model.addAllAttributes(cartData.getCartDataMap());
		model.addAttribute("cartData", cartData);

		double bettingAmount = betDto.getBetAmount();
		double odds = cartData.getOdds();
		double payout = bettingAmount * odds;
		betDto.setPayout(payout);

		model.addAttribute("betDto", betDto);

		System.out.println("Match Controller(navigateBetCart) cartData" + cartData);

		return "bet/BetCart";
	}

	@PostMapping("/submitBet")
	public String submitBet(@ModelAttribute BetDto betDto) {
		betService.saveBet(betDto);

		return "redirect:/bet/BetCart";
	}

	//다른 서버에서 넘어올 때 유저 정보 조회하고 메인으로 넘깁니다.
	@GetMapping("/room")
	public String rooms(HttpSession session, Model model) {

		String sid = session.getId();
		String uid = (String) session.getAttribute("user");
		System.out.println(sid + "::::::::" + uid);

		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:2114")
				.path("/valid/user/" + uid)
				.encode()
				.build()
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
		model.addAttribute("nickName", responseEntity.getBody().getNickName());
		// logger.info("[Port:1999] ChatRoomController");
		return "/chat/room";

	}
}
