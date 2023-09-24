package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.UserDto;
import com.hgyr.nonsanroy.data.dto.bet.BetCartDto;
import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 명원식
 * @classNote MatchController
 * @purpose
 */
@Controller
@RequestMapping("/bet")
public class MatchController {

	private final BetService betService;
	private final AtomicInteger latestBetNo = new AtomicInteger(1);

	@Autowired
	public MatchController(BetService betService) {
		this.betService = betService;
	}

	/**
	 * @methodNote navigateBetMain
	 * @purpose
	 * @author 명원식
	 */
	@GetMapping("/betMain")
	public String navigateBetMain(HttpSession session, Model model) {
		List<Match> matches = betService.getAllMatches();
		model.addAttribute("matches", matches);

		String sid = session.getId();
		String uid = (String) session.getAttribute("user");

		System.out.println(sid + "::::::::" + uid);

		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:1777")
				.path("/valid/user/" + uid)
				.encode()
				.build()
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
		// model.addAttribute("nickName", responseEntity.getBody().getNickName());

		Double userPoint = betService.getPoint(uid); // Retrieve user's current points
		model.addAttribute("userPoint", userPoint);
		model.addAttribute("uid", uid);

		return "/bet/BetMain"; // templates/bet/BetMain.html
	}

	/**
	 * @methodNote navigateBetCart
	 * @purpose
	 * @otherNotes Cannot use just @ModelAttribute while giving the tag to the map method in the Dto
	 * (Method Parameter Level vs Method Level)
	 * @author 명원식
	 */
	@PostMapping("/betCart/{uid}")
	public String navigateBetCart(@PathVariable String uid, @ModelAttribute("getCartDataMap") BetCartDto cartData, BetDto betDto, Model model, HttpSession session) {

		uid = (String) session.getAttribute("user");
		String sid = session.getId();
		betDto.setBetNo(latestBetNo.incrementAndGet()); // bet# 초기값 설정

		// LocalDateTime matchEnd = betService.findMatchEndByMatchNo(cartData.getMatchNo()); 포기

		Double userPoint = betService.getPoint(uid); // Retrieve user's current points
		model.addAttribute("userPoint", userPoint);
		model.addAttribute("cartData", cartData);
		// model.addAttribute("matchEnd", matchEnd);
		model.addAttribute("betDto", betDto);

		System.out.println("uid: " + uid);
		System.out.println("userPoint: " + userPoint);
		System.out.println("Match Controller(navigateBetCart) cartData" + cartData);

		return "bet/BetCart";
	}

	/* @GetMapping("/betIndex")
	public String navigateBetIndex(Model model, @ModelAttribute("nonDateDto") BetDto nonDateDto) {
	List<Bet> bets = betService.getAllBets();
		System.out.println(bets);
		model.addAttribute("bets", bets);
		model.addAttribute("nonDateDto", nonDateDto);

		return "bet/BetIndex"; // templates/bet/BetMain.html
} */

	@PostMapping("/submitBet/{betNo}")
	public String submitBet(@PathVariable Integer betNo, Model model, @ModelAttribute BetDto betDto) {

		List<Bet> bets = betService.getAllBets();
		Bet bet = new Bet();
		bet.setBetDate(null);

		model.addAttribute("bets", bets);
		model.addAttribute("betDto", betDto);
		System.out.println("bets :" + bets + "betDto + " + betDto);
		betService.saveBet(betDto);

		return "redirect:/bet/BetIndex/{betNo}";
	}

	@GetMapping("/point/{uid}") //실제 작업시에는 Post 방식으로
	public String getPoint(@PathVariable String uid){
		Double result = betService.getPoint(uid);
		System.out.println(result);
		return "redirect:/hgyr";
	}

	@GetMapping("/point/{uid}/{point}") //실제 작업시에는 Post 방식으로
	public String updatePoint(@PathVariable("uid") String uid, @PathVariable("point") String point){
		String result = betService.updatePoint(uid, point);
		System.out.println(result);

		return "redirect:/hgyr";
	}

	//다른 서버에서 넘어올 때 유저 정보 조회하고 메인으로 넘깁니다.
	/* @GetMapping("/room")
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
	 */
}
