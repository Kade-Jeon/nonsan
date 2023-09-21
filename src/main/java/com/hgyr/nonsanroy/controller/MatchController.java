package com.hgyr.nonsanroy.controller;

import com.hgyr.nonsanroy.data.dto.UserDto;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.service.BetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @classNote MatchController
 * @purpose
 * @RequiredFor
 * @author 명원식
 */
@Controller
@RequestMapping("/bet")
public class MatchController {

	private BetService betService;

	@GetMapping("/BetMain")
	public String showBetMain(Model model) {
		List<Match> matches = betService.getAllMatches();
		model.addAttribute("matches", matches);
		System.out.println("Match Controller: matches :" + matches);
		return "bet/BetMain";
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
