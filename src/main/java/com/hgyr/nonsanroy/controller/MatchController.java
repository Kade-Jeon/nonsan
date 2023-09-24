package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.UserDto;
import com.hgyr.nonsanroy.data.dto.bet.BetCartDto;
import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.dto.bet.PointDto;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.time.format.DateTimeFormatter;
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

	private final PointService pointService;
	private final BetService betService;
	private AtomicInteger latestBetNo = new AtomicInteger(1);

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
	@GetMapping("/betMain")
	public String navigateBetMain(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Match> matches = betService.getAllMatches();
		/**
		 * @declarationNote dateTimeFormatter
		 * @purpose appears as 2023-09-21T12:00 Prior to adding dates.format.
		 * Make use of thymeleaf: temporals.format in html
		 * <td th:text="${#temporals.format(match.matchStart, 'yyyy-MM-dd HH:mm')}"></td>
		 * <td th:text="${#temporals.format(match.matchEnd, 'yyyy-MM-dd HH:mm')}"></td>
		 * @author 명원식
		 */
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");

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

		betDto.setBetNo(latestBetNo.incrementAndGet());

		String userId = "uid1";
		PointDto userPoint = pointService.myPoint(userId); // Retrieve user's current points
		model.addAttribute("userPoint", userPoint);
		model.addAttribute("cartData", cartData);
		model.addAttribute("betDto", betDto);

		System.out.println("Match Controller(navigateBetCart) cartData" + cartData);

		return "bet/BetCart";
	}

	@GetMapping("/betIndex")
	public String navigateBetIndex(Model model) {
	List<Bet> bets = betService.getAllBets();
		System.out.println(bets);
		model.addAttribute("bets", bets);
		return "bet/BetIndex"; // templates/bet/BetMain.html
}

	@PostMapping("/submitBet")
	public String submitBet(@ModelAttribute BetDto betDto) {
		betService.saveBet(betDto);

		return "redirect:/betIndex";
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
