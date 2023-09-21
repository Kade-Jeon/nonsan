package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.UserDto;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @classNote MatchController
 * @purpose
 * @author 명원식
 */
@Controller
@RequestMapping("/bet")
public class MatchController {

	private final BetService betService;

	@Autowired
	public MatchController(BetService betService) {
		this.betService = betService;
	}

	@GetMapping("/BetMain")
	public String showBetMain(Model model) {
		List<Match> matches = betService.getAllMatches();

		// LocalDateTime localDateTime = LocalDateTime.now();
		/**
		 * @declarationNote dateTimeFormatter
		 * @purpose appears as 2023-09-21T12:00 Prior to adding dates.format.
		 * Make use of thymeleaf: temporals.format in html
		 * <td th:text="${#temporals.format(match.matchStart, 'yyyy-MM-dd HH:mm')}"></td>
		 * <td th:text="${#temporals.format(match.matchEnd, 'yyyy-MM-dd HH:mm')}"></td>
		 * @author 명원식
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//String formattedDateTime = localDateTime.format(formatter);
		//model.addAttribute("formattedDateTime", formattedDateTime);

		System.out.println("Match Controller: matches :" + matches);
		model.addAttribute("matches", matches);
		return "bet/BetMain"; // templates/bet/BetMain.html
	}

	private Set<Map<String, String>> userList = new HashSet<>();
	//유저 정보 받아오기
	@PostMapping("/valid")
	@ResponseBody
	public void checkUser(@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto);
		if(userDto == null){
			throw new Exception();
		}
		Map<String, String> temp = new HashMap<>();
		temp.put(userDto.getUid(), userDto.getNickName());
		System.out.println(temp);
		userList.add(temp);
	}

}
